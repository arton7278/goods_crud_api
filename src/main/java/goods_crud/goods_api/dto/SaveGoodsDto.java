package goods_crud.goods_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import goods_crud.goods_api.common.GenderType;
import goods_crud.goods_api.common.GoodsType;
import goods_crud.goods_api.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class SaveGoodsDto {

        @lombok.Getter
        @lombok.Setter
        @lombok.ToString
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static  class Request {
                //상품 이름
                private String goodsNm;
                //상품 설명
                private String goodsCont;
                //상품 타입
                private String goodsType;
                //상품가격
                private Long goodsPrice;
                //상품 할인율
                private Long discountRate;
                //성별구분
                private String genderType;
                //상품품번
                private String partNumber;
                //카테고리 넘버
                private Integer categoryNo;
                //업체 Id
                private String comId;
                //브랜드 Id
                private String brandId;
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
