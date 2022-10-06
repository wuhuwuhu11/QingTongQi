package zxk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import zxk.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 */
public interface PmsBrandService extends IService<PmsBrand> {
    IPage<PmsBrand> list(Integer pageNo, Integer pageSize, String value);
    Boolean add(String name, MultipartFile file, String description);
    Boolean check(String fieldName, String value, Long id);
    Boolean update(Long id, String name, MultipartFile file, String description);
    Boolean del(Long id, Integer active);
}
