package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import zxk.api.FileService;
import zxk.entity.PmsBrand;
import zxk.mapper.PmsBrandMapper;
import zxk.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {
    @Resource
    FileService fileService;
    @Override
    public IPage<PmsBrand> list(Integer pageNo, Integer pageSize, String value) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<PmsBrand>();
        wrapper.orderByDesc("id");
        if(StringUtils.isNotBlank(value)) {
            wrapper.like("name", value);
        }
        return this.page(new Page<PmsBrand>(pageNo, pageSize), wrapper);
    }

    @Override
    public Boolean add(String name, MultipartFile file, String description) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setName(name);
        pmsBrand.setDescription(description);
        pmsBrand.setLogo(fileService.upload(file, "image"));
        return this.save(pmsBrand);
    }

    @Override
    public Boolean check(String fieldName, String value, Long id) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<PmsBrand>();
        wrapper.eq(fieldName, value);
        if(id != null) {
            wrapper.ne("id", id);
        }
        return this.count(wrapper) == 0;
    }

    @Override
    public Boolean update(Long id, String name, MultipartFile file, String description) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId(id);
        pmsBrand.setName(name);
        pmsBrand.setDescription(description);
        if(file != null && file.getSize() > 0) {
            pmsBrand.setLogo(fileService.upload(file, "image"));
        }
        return this.updateById(pmsBrand);
    }

    @Override
    public Boolean del(Long id, Integer active) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setId(id);
        pmsBrand.setActive(active);
        return this.updateById(pmsBrand);
    }
}
