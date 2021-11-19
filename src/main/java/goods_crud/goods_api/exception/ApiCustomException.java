package goods_crud.goods_api.exception;

import goods_crud.goods_api.common.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiCustomException extends RuntimeException {
    private ErrorCode errorCode;

    private Map<String, Object> errorMap;

    public ApiCustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiCustomException(String message, Map<String,Object> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}
