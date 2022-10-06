package zxk.controller;

import zxk.service.UmsResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import zxk.common.ResultJson;
import zxk.service.UmsRoleResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色关联资源 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/umsRoleResource")
public class UmsRoleResourceController {
    @Resource
    UmsRoleResourceService umsRoleResourceService;
    @Resource
    UmsResourceService resourceService;
    @GetMapping("/getData")
    ResultJson<Map<String, List>> getData(Long roleId) {
        Map<String, List> map = new HashMap<>();
        map.put("resource", resourceService.getAll());
        map.put("values", umsRoleResourceService.getByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    ResultJson<Boolean> save(Long roleId, Long[] menus, Long[] btns) {
        return ResultJson.success(umsRoleResourceService.save(roleId, menus, btns),"关联资源成功");
    }
}
