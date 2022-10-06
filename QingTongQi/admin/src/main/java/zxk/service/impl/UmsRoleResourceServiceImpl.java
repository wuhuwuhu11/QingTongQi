package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import zxk.entity.UmsRoleResource;
import zxk.mapper.UmsRoleResourceMapper;
import zxk.service.UmsRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色关联资源 服务实现类
 * </p>
 */
@Service
public class UmsRoleResourceServiceImpl extends ServiceImpl<UmsRoleResourceMapper, UmsRoleResource> implements UmsRoleResourceService {

    @Override
    @Transactional
    public Boolean save(Long roleId, Long[] menus, Long[] btns) {
        UpdateWrapper<UmsRoleResource> wrapper = new UpdateWrapper<>();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);
        List<UmsRoleResource> list = new ArrayList<>();
        if(menus != null) {
            for(Long menuId : menus) {
                list.add(new UmsRoleResource(roleId, menuId, 1));
            }
        }
        if(btns != null) {
            for(Long btnId : btns) {
                list.add(new UmsRoleResource(roleId, btnId, 0));
            }
        }
        return this.saveBatch(list);
    }

    @Override
    public List<UmsRoleResource> getByRoleId(Long roleId) {
        QueryWrapper<UmsRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return this.list(wrapper);
    }
}
