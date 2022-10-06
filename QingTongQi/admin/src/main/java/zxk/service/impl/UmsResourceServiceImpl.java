package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import zxk.common.WyException;
import zxk.entity.UmsResource;
import zxk.mapper.UmsResourceMapper;
import zxk.service.UmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {
    @Resource
    UmsResourceMapper resourceMapper;
    @Override
    @Cacheable(value = "ums", key = "'resources'")
    public List<UmsResource> getAll() {
        return getByParentId(0L);
    }
    private List<UmsResource> getByParentId(Long parentId) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<UmsResource> list = this.list(wrapper);
        for(UmsResource umsResource : list) {
            List<UmsResource> children = getByParentId(umsResource.getId());
            umsResource.setChildren(children);
        }
        return list;
    }
    @Override
    @CacheEvict(value = "ums", key = "'resources'")
    public Boolean add(String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl) {
        UmsResource umsResource = new UmsResource(
                name
                ,type
                ,level
                ,parentId
                ,frontUrl
                ,backUrl
        );
        return this.save(umsResource);
    }

    @Override
    public Boolean check(String fieldName, String value, Long id) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq(fieldName, value);
        if(id != null) {
            wrapper.ne("id", id);
        }
        return this.count(wrapper) == 0;
    }

    @Override
    @CacheEvict(value = "ums", key = "'resources'")
    public Boolean update(Long id, String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl) {
        UmsResource umsResource = new UmsResource(
                name
                ,type
                ,level
                ,parentId
                ,frontUrl
                ,backUrl
        );
        umsResource.setId(id);
        return this.updateById(umsResource);
    }

    @Override
    @CacheEvict(value = "ums", key = "'resources'")
    public Boolean del(Long id) throws Exception {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        if(this.count(wrapper) > 0) {
            throw new WyException("存在下级数据,无法进行删除");
        }
        return this.removeById(id);
    }

    @Override
    public List<UmsResource> getByUserId(Long userId) {
        return resourceMapper.getByUserId(userId);
    }
}
