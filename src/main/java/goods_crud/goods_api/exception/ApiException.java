package goods_crud.goods_api.exception;

import goods_crud.goods_api.common.ErrorCode;

import java.util.Map;

public class ApiException extends RuntimeException {
    private ErrorCode errorCode;

    private Map<String, Object> errorMap;

    public ApiException(Exception e) { super(e);}

    public ApiException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ApiException(String message, Map<String,Object> errorMap){
        super(message);
        this.errorMap = errorMap;
    }
}
