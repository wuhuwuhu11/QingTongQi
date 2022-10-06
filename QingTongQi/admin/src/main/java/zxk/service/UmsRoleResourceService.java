package zxk.service;

import zxk.entity.UmsRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色关联资源 服务类
 * </p>
 */
public interface UmsRoleResourceService extends IService<UmsRoleResource> {
    Boolean save(Long roleId, Long[] menus, Long[] btns);
    List<UmsRoleResource> getByRoleId(Long roleId);
}
