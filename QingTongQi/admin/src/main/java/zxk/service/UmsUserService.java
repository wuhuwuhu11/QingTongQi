package zxk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import zxk.common.WyException;
import zxk.entity.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface UmsUserService extends IService<UmsUser> {
    IPage<UmsUser> list(Integer pageNo, Integer pageSize, String value);
    Boolean add(String name, String phone, String email, String password, MultipartFile file);
    Boolean check(String fieldName, String value, Long id);
    Boolean update(Long id, String name, String phone, String email, MultipartFile file);
    Boolean del(Long id, Integer active);
    List<UmsUser> getActive();
    Map<String, Object> login(String username, String password) throws WyException;
}
