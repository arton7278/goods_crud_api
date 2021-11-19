package goods_crud.goods_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@ToString
public class UpdateGoodsDto {

        @lombok.Getter
        @lombok.Setter
        @ToString
        public static  class Request {
                private Long goodsNo;
                //상품 이름
                @NotEmpty
                private String goodsNm;
                //상품 설명
                private String goodsCont;
                //상품 타입
                @NotEmpty
                private String goodsType;
                //상품가격
                @Min(1000)
                private Long goodsPrice;
                //상품 할인율
                private Long discountRate;
                //성별구분
                @NotEmpty
                private String genderType;
                //상품품번
                private String partNumber;
                //카테고리 넘버
                private Integer categoryNo;
                //업체 Id
                private String comId;
                //노출여부
                private Boolean isShow;
                //등록자 Id
                private String regUserId;
                //수정 Id
                private String updUserId;
        }
        @lombok.Getter
        @lombok.Setter
        @ToString
        public static class Response {
                private Long goodsNo;
                public Response toDo(Long goodsNo){
                        this.goodsNo = goodsNo;
                        return this;
                }
        }
}
