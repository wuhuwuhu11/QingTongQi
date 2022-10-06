package zxk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestMapping;
import zxk.common.ResultJson;
import zxk.entity.PmsBrand;
import zxk.service.PmsBrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {
    @Resource
    PmsBrandService pmsBrandService;
    @GetMapping("/list")
    ResultJson<IPage<PmsBrand>> list(Integer pageNo, Integer pageSize, String value) {
        return ResultJson.success(pmsBrandService.list(pageNo, pageSize, value));
    }
    @PostMapping("/add")
    ResultJson<Boolean> add(String name, MultipartFile file, String description) {
        return ResultJson.success(pmsBrandService.add(name, file, description),"添加品牌表成功");
    }
    @GetMapping("/check")
    ResultJson<Boolean> check(String fieldName, String value, Long id) {
        return ResultJson.success(pmsBrandService.check(fieldName, value, id));
    }
    @GetMapping("/getone")
    ResultJson<PmsBrand> getone(Long id) {
        return ResultJson.success(pmsBrandService.getById(id));
    }
    @PostMapping("/update")
    ResultJson<Boolean> update(Long id, String name, MultipartFile file, String description) {
        return ResultJson.success(pmsBrandService.update(id, name, file, description), "修改品牌表成功");
    }
    @PostMapping("/del")
    ResultJson<Boolean> del(Long id, Integer active) {
        return ResultJson.success(pmsBrandService.del(id, active), active == 0 ? "删除品牌表成功" : "恢复品牌表成功");
    }
}
