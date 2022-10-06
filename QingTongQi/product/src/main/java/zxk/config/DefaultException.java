package zxk.config;

import zxk.common.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultException {
    @ExceptionHandler
    ResultJson defaultExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ResultJson.failed("系统错误,请联系管理员");
    }
}
