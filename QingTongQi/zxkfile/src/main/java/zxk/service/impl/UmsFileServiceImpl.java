package zxk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import zxk.entity.UmsFile;
import zxk.mapper.UmsFileMapper;
import zxk.service.UmsFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 */
@Service
public class UmsFileServiceImpl extends ServiceImpl<UmsFileMapper, UmsFile> implements UmsFileService {

    @Override
    public UmsFile get(String md5, long size, String suffix) {
        QueryWrapper<UmsFile> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        return this.getOne(wrapper);
    }
}
