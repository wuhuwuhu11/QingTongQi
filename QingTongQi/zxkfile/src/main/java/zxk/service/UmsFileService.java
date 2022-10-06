package zxk.service;

import zxk.entity.UmsFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文件表 服务类
 * </p>
 */
public interface UmsFileService extends IService<UmsFile> {
    UmsFile get(String md5, long size, String suffix);
}
