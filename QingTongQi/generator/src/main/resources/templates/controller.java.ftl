package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestMapping;
import com.powernode.common.ResultJson;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Resource
    ${table.serviceName} ${table.serviceName?uncap_first};
    @GetMapping("/list")
    ResultJson<IPage> list(Integer pageNo, Integer pageSize, String value) {
        return ResultJson.success(${table.serviceName?uncap_first}.list(pageNo, pageSize, value));
    }
    @PostMapping("/add")
    ResultJson<Boolean> add() {
        return ResultJson.success(${table.serviceName?uncap_first}.add(),"添加${table.comment!}成功");
    }
    @GetMapping("/check")
    ResultJson<Boolean> check(String fieldName, String value, Long id) {
        return ResultJson.success(${table.serviceName?uncap_first}.check(fieldName, value, id));
    }
    @GetMapping("/getone")
    ResultJson<${entity}> getone(Long id) {
        return ResultJson.success(${table.serviceName?uncap_first}.getById(id));
    }
    @PostMapping("/update")
    ResultJson<Boolean> update() {
        return ResultJson.success(${table.serviceName?uncap_first}.update(), "修改${table.comment!}成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(Long id, Integer active) {
        return ResultJson.success(${table.serviceName?uncap_first}.del(id, active), active == 0 ? "删除${table.comment!}成功" : "恢复${table.comment!}成功");
    }
}
</#if>
