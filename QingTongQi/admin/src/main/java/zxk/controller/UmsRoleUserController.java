package zxk.controller;

import zxk.service.UmsUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import zxk.common.ResultJson;
import zxk.service.UmsRoleUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户与角色关系表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/umsRoleUser")
public class UmsRoleUserController {
    @Resource
    UmsRoleUserService umsRoleUserService;
    @Resource
    UmsUserService umsUserService;
    @GetMapping("/getData")
    ResultJson getData(Long roleId) {
        Map<String, List> map = new HashMap<>();
        map.put("users", umsUserService.getActive());
        map.put("values", umsRoleUserService.getByRoleId(roleId));
        return ResultJson.success(map);
    }
    @PostMapping("/save")
    ResultJson<Boolean> save(Long roleId, Long[] userIds) {
        return ResultJson.success(umsRoleUserService.save(roleId, userIds), "关联用户成功");
    }
}
