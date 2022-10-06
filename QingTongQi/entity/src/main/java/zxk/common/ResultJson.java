package zxk.common;

import lombok.Getter;

@Getter
public class ResultJson<T> {
    private Integer code;
    private T content;
    private String message;
    private ResultJson(ResultCode resultCode, T content, String message) {
        this.code = resultCode.getCode();
        this.content = content;
        this.message = message;
    }
    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T content, String message) {
        return new ResultJson(resultCode, content, message);
    }
    public static <T> ResultJson<T> success(T content, String message) {
        return getInstance(ResultCode.SUCCESS, content, message);
    }
    public static <T> ResultJson<T> success(T content) {
        return success(content, "");
    }
    public static <T> ResultJson<T> failed(T content, String message) {
        return getInstance(ResultCode.FAILED, content, message);
    }
    public static <T> ResultJson<T> failed(String message) {
        return failed(null, message);
    }

    public static <T> ResultJson<T> unauthentication(String message) {
        return getInstance(ResultCode.UNAUTHENTICATION, null, message);
    }
    public static <T> ResultJson forbid() {
        return getInstance(ResultCode.FORBID, null, "该用户无此操作权限");
    }
}
