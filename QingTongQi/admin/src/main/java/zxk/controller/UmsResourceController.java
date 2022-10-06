package zxk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import zxk.common.ResultJson;
import zxk.entity.UmsResource;
import zxk.service.UmsResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/umsResource")
public class UmsResourceController {
    @Resource
    UmsResourceService umsResourceService;
    @GetMapping("/list")
    ResultJson<List> list() {
        return ResultJson.success(umsResourceService.getAll());
    }
    @PostMapping("/add")
    ResultJson<Boolean> add(String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl) {

        return ResultJson.success(umsResourceService.add(name, type, level, parentId, frontUrl, backUrl),"添加资源表成功");
    }
    @GetMapping("/check")
    ResultJson<Boolean> check(String fieldName, String value, Long id) {
        return ResultJson.success(umsResourceService.check(fieldName, value, id));
    }
    @GetMapping("/getone")
    ResultJson<UmsResource> getone(Long id) {
        return ResultJson.success(umsResourceService.getById(id));
    }
    @PostMapping("/update")
    ResultJson<Boolean> update(Long id, String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl) {
        return ResultJson.success(umsResourceService.update(id, name, type, level, parentId, frontUrl, backUrl), "修改资源表成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(Long id) throws Exception {
        return ResultJson.success(umsResourceService.del(id) ,"删除资源表成功");
    }
}
