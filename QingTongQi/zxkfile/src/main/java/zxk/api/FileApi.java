package zxk.api;

import zxk.service.FileService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/file")
public class FileApi {
    @Resource
    FileService fileService;
    @PostMapping("/upload")
    String upload(@RequestPart("file") MultipartFile file, @RequestParam("bucket") String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InternalException {
        return fileService.upload(file, bucket);
    }
}
