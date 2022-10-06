package zxk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestMapping;
import zxk.common.ResultJson;
import zxk.entity.UmsRole;
import zxk.service.UmsRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/umsRole")
public class UmsRoleController {
    @Resource
    UmsRoleService umsRoleService;
    @GetMapping("/list")
    ResultJson<IPage> list(Integer pageNo, Integer pageSize, String value) {
        return ResultJson.success(umsRoleService.list(pageNo, pageSize, value));
    }
    @PostMapping("/add")
    ResultJson<Boolean> add(String name) {
        return ResultJson.success(umsRoleService.add(name),"添加角色表成功");
    }
    @GetMapping("/check")
    ResultJson<Boolean> check(String fieldName, String value, Long id) {
        return ResultJson.success(umsRoleService.check(fieldName, value, id));
    }
    @GetMapping("/getone")
    ResultJson<UmsRole> getone(Long id) {
        return ResultJson.success(umsRoleService.getById(id));
    }
    @PostMapping("/update")
    ResultJson<Boolean> update(Long id, String name) {
        return ResultJson.success(umsRoleService.update(id, name), "修改角色表成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(Long id, Integer active) {
        return ResultJson.success(umsRoleService.del(id, active), active == 0 ? "删除角色表成功" : "恢复角色表成功");
    }
}
