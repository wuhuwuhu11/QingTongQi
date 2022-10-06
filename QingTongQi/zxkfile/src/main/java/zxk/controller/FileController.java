package zxk.controller;

import zxk.common.ResultJson;
import zxk.service.FileService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/pmsFile")
public class FileController {
    @Resource
    FileService fileService;
    @PostMapping("/upload")
    ResultJson<String> upload(MultipartFile file, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InternalException {
        return ResultJson.success(fileService.upload(file, bucket));
    }
}
