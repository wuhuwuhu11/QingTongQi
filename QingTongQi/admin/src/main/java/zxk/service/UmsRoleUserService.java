package zxk.service;

import zxk.entity.UmsRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与角色关系表 服务类
 * </p>
 */
public interface UmsRoleUserService extends IService<UmsRoleUser> {
    Boolean save(Long roleId, Long[] userIds);
    List<UmsRoleUser> getByRoleId(Long roleId);
}
