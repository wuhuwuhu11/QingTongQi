package zxk.service;

import zxk.entity.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 */
public interface UmsResourceService extends IService<UmsResource> {
    List<UmsResource> getAll();
    Boolean add(String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl);
    Boolean check(String fieldName, String value, Long id);
    Boolean update(Long id, String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl);
    Boolean del(Long id) throws Exception;
    List<UmsResource> getByUserId(Long userId);
}
