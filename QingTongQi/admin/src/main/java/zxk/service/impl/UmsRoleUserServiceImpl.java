package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import zxk.entity.UmsRoleUser;
import zxk.mapper.UmsRoleUserMapper;
import zxk.service.UmsRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 */
@Service
public class UmsRoleUserServiceImpl extends ServiceImpl<UmsRoleUserMapper, UmsRoleUser> implements UmsRoleUserService {

    @Override
    @Transactional
    public Boolean save(Long roleId, Long[] userIds) {
        UpdateWrapper<UmsRoleUser> wrapper = new UpdateWrapper<>();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);
        List<UmsRoleUser> list = new ArrayList<>();
        if (userIds != null) {
            for (Long userId : userIds) {
                list.add(new UmsRoleUser(roleId, userId));
            }
        }
        return this.saveBatch(list);
    }

    @Override
    public List<UmsRoleUser> getByRoleId(Long roleId) {
        QueryWrapper<UmsRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return this.list(wrapper);
    }
}
