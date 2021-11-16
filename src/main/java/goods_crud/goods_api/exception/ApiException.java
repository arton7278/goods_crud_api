package goods_crud.goods_api.exception;

import goods_crud.goods_api.common.ErrorCode;

public class ApiException extends RuntimeException {
    private ErrorCode errorCode;
}
