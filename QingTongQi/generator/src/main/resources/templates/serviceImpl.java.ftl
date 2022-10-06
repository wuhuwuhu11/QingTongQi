package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    @Override
    public IPage<${entity}> list(Integer pageNo, Integer pageSize, String value) {
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        if(StringUtils.isNotBlank(value)) {
            wrapper.like("name", value);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
    @Override
    public Boolean add() {
        ${entity} ${entity?uncap_first} = new ${entity}();
        return this.save(${entity?uncap_first});
    }

    @Override
    public Boolean check(String fieldName, String value, Long id) {
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
        wrapper.eq(fieldName, value);
        if(id != null) {
            wrapper.ne("id", id);
        }
        return this.count(wrapper) == 0;
    }

    @Override
    public Boolean update() {
        ${entity} ${entity?uncap_first} = new ${entity}();
        ${entity?uncap_first}.setId(id);
        return this.updateById(${entity?uncap_first});
    }

    @Override
    public Boolean del(Long id, Integer active) {
        ${entity} ${entity?uncap_first} = new ${entity}();
        ${entity?uncap_first}.setId(id);
        ${entity?uncap_first}.setActive(active);
        return this.updateById(${entity?uncap_first});
    }
}
</#if>
