package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import zxk.entity.UmsRole;
import zxk.mapper.UmsRoleMapper;
import zxk.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {
    @Override
    public IPage<UmsRole> list(Integer pageNo, Integer pageSize, String value) {
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        if(StringUtils.isNotBlank(value)) {
            wrapper.like("name", value);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
    @Override
    public Boolean add(String name) {
        UmsRole umsRole = new UmsRole(name, null);
        return this.save(umsRole);
    }

    @Override
    public Boolean check(String fieldName, String value, Long id) {
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        wrapper.eq(fieldName, value);
        if(id != null) {
            wrapper.ne("id", id);
        }
        return this.count(wrapper) == 0;
    }

    @Override
    public Boolean update(Long id, String name) {
        UmsRole umsRole = new UmsRole();
        umsRole.setId(id);
        umsRole.setName(name);
        return this.updateById(umsRole);
    }

    @Override
    public Boolean del(Long id, Integer active) {
        UmsRole umsRole = new UmsRole();
        umsRole.setId(id);
        umsRole.setActive(active);
        return this.updateById(umsRole);
    }
}
