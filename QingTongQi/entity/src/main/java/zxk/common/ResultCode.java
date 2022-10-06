package zxk.common;

public enum ResultCode {
    SUCCESS(200),
    FAILED(500),
    UNAUTHENTICATION(401),
    FORBID(403);
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
