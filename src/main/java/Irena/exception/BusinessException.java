package Irena.exception;

public class BusinessException extends BaseException {
    public BusinessException(String code, String message) {
        super("BUS"+code, "业务逻辑异常"+message);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super("BUS"+code, "业务逻辑异常"+message, cause);
    }

}
