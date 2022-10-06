package zxk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import zxk.entity.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 */
public interface UmsRoleService extends IService<UmsRole> {
    IPage<UmsRole> list(Integer pageNo, Integer pageSize, String value);
    Boolean add(String name);
    Boolean check(String fieldName, String value, Long id);
    Boolean update(Long id, String name);
    Boolean del(Long id, Integer active);
}
