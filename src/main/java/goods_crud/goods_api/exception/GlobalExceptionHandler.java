package goods_crud.goods_api.exception;

import goods_crud.goods_api.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.QueryException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        return builder.toString();
    }


    @ExceptionHandler(QueryException.class)
    public String handleQueryException(QueryException exception) {
        log.error("queryException : {}", exception.getQueryString());
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public String handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception) {
        log.error("queryException : {}", exception.getMessage());
        exception.printStackTrace();
        return exception.getMessage();
    }


    @ExceptionHandler(ApiCustomException.class)
    public ResponseEntity<ErrorResponseDto> handleApiCustomException(ApiCustomException exception) {
        log.info("ApiCustomException =====> {} " , exception);
        ErrorResponseDto errorResponseDto =  new ErrorResponseDto(exception.getErrorCode());

        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
//        return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
