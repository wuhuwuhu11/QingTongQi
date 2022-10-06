package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import zxk.api.FileService;
import zxk.common.JwtUitl;
import zxk.common.WyException;
import zxk.entity.UmsResource;
import zxk.entity.UmsUser;
import zxk.mapper.UmsUserMapper;
import zxk.service.UmsResourceService;
import zxk.service.UmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    FileService fileService;
    @Resource
    UmsResourceService resourceService;
    @Resource(name = "redisTemplate")
    RedisTemplate<String, Object> redisTemplate;
    @Override
    public IPage<UmsUser> list(Integer pageNo, Integer pageSize, String value) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        if(StringUtils.isNotBlank(value)) {
            wrapper.like("name", value)
                    .or().like("phone", value)
                    .or().like("email", value);
        }
        return this.page(new Page<UmsUser>(pageNo, pageSize), wrapper);
    }
    @Override
    public Boolean add(String name, String phone, String email, String password, MultipartFile file) {
        String path = fileService.upload(file, "image");
        UmsUser umsUser = new UmsUser(
                name
                , phone
                , email
                , path
                , null
                , passwordEncoder.encode(password)
        );
        return this.save(umsUser);
    }

    @Override
    public Boolean check(String fieldName, String value, Long id) {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq(fieldName, value);
        if(id != null) {
            wrapper.ne("id", id);
        }
        return this.count(wrapper) == 0;
    }

    @Override
    public Boolean update(Long id, String name, String phone, String email, MultipartFile file) {
        String path = null;
        if (file != null && file.getSize() != 0) {
            path = fileService.upload(file, "image");
        }
        UmsUser umsUser = new UmsUser(
                name
                , phone
                , email
                , path
                , null
                , null
        );
        umsUser.setId(id);
        return this.updateById(umsUser);
    }

    @Override
    public Boolean del(Long id, Integer active) {
        UmsUser umsUser = new UmsUser();
        umsUser.setId(id);
        umsUser.setActive(active);
        return this.updateById(umsUser);
    }

    @Override
    public List<UmsUser> getActive() {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public Map<String, Object> login(String username, String password) throws WyException {
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", username)
                .or().eq("email", username);
        UmsUser user = this.getOne(wrapper);
        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new WyException("用户名或密码错误");
        }
        if(user.getActive() == 0) {
            throw new WyException("该用户已经失效,无法登录");
        }
        List<UmsResource> list = resourceService.getByUserId(user.getId());
        List<UmsResource> menus = getMenu(list);
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtUitl.create(user));
        map.put("menus", menus);
        // 拿到后端地址列表
        Set<String> backUrls = getBackUrls(list);
        // 写入缓存
        String key = "ums::" + user.getId() + "::session";
        redisTemplate.opsForValue().set(key, backUrls, 30, TimeUnit.MINUTES);
        return map;
    }
    private List<UmsResource> getMenu(List<UmsResource> source) {
        List<UmsResource> menus = new ArrayList<>();
        for(UmsResource resource : source) {
            if(resource.getParentId() == 0) {
                menus.add(resource);
                continue;
            }
            if(resource.getType() == 1) {
                for(UmsResource parent : source) {
                    if(parent.getId().longValue() == resource.getParentId().longValue()) {
                        parent.getChildren().add(resource);
                        break;
                    }
                }
            }
        }
        return menus;
    }
    private Set<String> getBackUrls(List<UmsResource> source) {
        Set<String> backUrls = new HashSet<>();
        for (UmsResource resource : source) {
            if(StringUtils.isNotBlank(resource.getBackUrl())) {
                backUrls.add(resource.getBackUrl());
            }
        }
        return backUrls;
    }
}
