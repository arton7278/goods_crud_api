package goods_crud.goods_api.dto;

import goods_crud.goods_api.common.ErrorCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private int status;
    private String code;
    private String message;
    public ErrorResponseDto(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
