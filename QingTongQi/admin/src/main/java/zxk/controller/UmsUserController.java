package zxk.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import zxk.common.ResultJson;
import zxk.common.WyException;
import zxk.entity.UmsUser;
import zxk.service.UmsUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/umsUser")
public class UmsUserController {
    @Resource
    UmsUserService umsUserService;
    @GetMapping("/list")
    ResultJson<IPage> list(Integer pageNo, Integer pageSize, String value, Long openId) throws WyException {

        System.out.println(openId);
        return ResultJson.success(umsUserService.list(pageNo, pageSize, value));
    }
    @PostMapping("/add")
    ResultJson<Boolean> add(String name, String phone, String email, String password, MultipartFile file) throws IOException {
        return ResultJson.success(umsUserService.add(name, phone, email, password, file),"添加用户成功");
    }
    @GetMapping("/check")
    ResultJson<Boolean> check(String fieldName, String value, Long id) {
        return ResultJson.success(umsUserService.check(fieldName, value, id));
    }
    @GetMapping("/getone")
    ResultJson<UmsUser> getone(Long id) {
        return ResultJson.success(umsUserService.getById(id));
    }
    @PostMapping("/update")
    ResultJson<Boolean> update(Long id, String name, String phone, String email, MultipartFile file) {
        return ResultJson.success(umsUserService.update(id, name, phone, email, file), "修改用户成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(Long id, Integer active) {
        return ResultJson.success(umsUserService.del(id, active), active == 0 ? "删除用户成功" : "恢复用户成功");
    }
    @PostMapping("/login")
    ResultJson<Map<String, Object>> login(String username, String password) throws WyException {
        return ResultJson.success(umsUserService.login(username, password));
    }
}
