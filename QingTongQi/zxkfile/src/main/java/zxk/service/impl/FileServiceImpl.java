package zxk.service.impl;

import zxk.entity.UmsFile;
import zxk.service.FileService;
import zxk.service.UmsFileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    UmsFileService umsFileService;
    @Value("${minio.endpoint}")
    String endpoint;
    @Value("${minio.username}")
    String username;
    @Value("${minio.password}")
    String password;
    @Override
    public String upload(MultipartFile file, String bucket) throws IOException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {
        // 拿到 md5 文件大小  后缀名
        String md5 = DigestUtils.md5Hex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        // 通过 md5 文件大小 后缀名 判断 是否该文件被上传过
        UmsFile umsFile = umsFileService.get(md5, size, suffix);
        if(umsFile != null) {
            return umsFile.getPath();
        }
        // 重新命名
        StringBuilder builder = new StringBuilder();
        // 拼接当前时间
        builder.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        // 拼接6位随机数
        builder.append(RandomStringUtils.random(6, false, true));
        // 拼接后缀名
        builder.append(".").append(suffix);
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(username, password)
                .build();
        PutObjectArgs args = PutObjectArgs.builder()
                .object(builder.toString())
                .bucket(bucket)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), size, 0)
                .build();
        minioClient.putObject(args);
        // 定义path
        String path = bucket + "/" + builder.toString();
        // 上传之后存入数据库
        umsFile = new UmsFile(md5, size, suffix, path);
        umsFileService.save(umsFile);
        return path;
    }
}
