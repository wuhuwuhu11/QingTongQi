package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    IPage<${entity}> list(Integer pageNo, Integer pageSize, String value);
    Boolean add();
    Boolean check(String fieldName, String value, Long id);
    Boolean update();
    Boolean del(Long id, Integer active);
}
</#if>
