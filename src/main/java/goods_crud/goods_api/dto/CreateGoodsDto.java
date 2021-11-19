package goods_crud.goods_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ToString
public class CreateGoodsDto {
        @lombok.Getter
        @lombok.Setter
        @lombok.ToString
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static  class Request {
                //상품 이름
                @NotBlank
                private String goodsNm;
                //상품가격
                @NotNull @Min(0)
                private Long goodsPrice;
                //성별구분
                @NotBlank
                private String genderType;
                //카테고리 넘버
                @NotNull
                private Integer categoryNo;
                //업체 Id
                @NotBlank
                private String comId;
                //상품 설명
                private String goodsCont;
                //상품 타입
                private String goodsType;
                //상품 할인율
                private Long discountRate;
                //상품품번
                private String partNumber;
                //노출여부
                private Boolean isShow;
                //등록자 Id
                private String regUserId;
                //수정 Id
                private String updUserId;
        }
        @lombok.Getter
        @lombok.Setter
        @lombok.ToString
        public static class Response {
                private Long goodsNo;
                public Response toDo(Long goodsNo){
                        this.goodsNo = goodsNo;
                        return this;
                }
        }
}
