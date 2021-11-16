package goods_crud.goods_api.domain;

import goods_crud.goods_api.dto.SaveGoodsDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


//CREATE TABLE `goods` (
//        `goods_no` int(11) NOT NULL DEFAULT '0' COMMENT '상품번호',
//        `goods_nm` varchar(100) DEFAULT NULL COMMENT '상품명',
//        `goods_cont` text COMMENT '상품설명',
//        `goods_type` varchar(100) DEFAULT NULL COMMENT '상품 타입(단독 상품, 세일 상품, 타입세일, 품절 포함)',
//        `goods_price` int(11) NOT NULL DEFAULT '0' COMMENT '상품가격',
//        `discount rate` int(11) NOT NULL DEFAULT '0' COMMENT '할인율',
//        `gender_type` varchar(10) DEFAULT NULL COMMENT 'unisex, man, woman'
//        `part_number`  varchar(100) DEFAULT NULL COMMENT '품번 ',
//        `category_no` int(11) DEFAULT NULL  COMMENT '카테고리 No',
//        `com_id` varchar(20) DEFAULT NULL COMMENT '업체 아이디',
//        `brand_id` varchar(20) DEFAULT NULL COMMENT '업체 아이디',
//        `is_show` int(1) NULL DEFAULT '0' COMMENT '노출여부',
//        `is_temp` int(1) NULL DEFAULT '0' COMMENT '임시저장여부',
//        `reg_dm` datetime DEFAULT NULL COMMENT '상품정보 최초등록일시',
//        `reg_user_no` int(11) DEFAULT NULL COMMENT '등록자 no',
//        `upd_dm` datetime DEFAULT NULL COMMENT '상품정보 수정일시',
//        `upd_user_no` int(11) DEFAULT NULL COMMENT '수정자 no'
//        PRIMARY KEY (`goods_no`)
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='상품마스터'
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {
    //goods 시리얼
    @Id @GeneratedValue
    private Long goodsNo;
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
    //등록시간
    private LocalDateTime regDm;
    //등록자 Id
    private String regUserId;
    //수정 시간
    private LocalDateTime updDm;
    //수정 Id
    private String updUserId;

    public static Goods createGoods(SaveGoodsDto.Request request){
        Goods goods = new Goods();
        goods.setGoodsNm(request.getGoodsNm());
        goods.setGoodsCont(request.getGoodsCont());
        goods.setGoodsType(request.getGoodsType());
        goods.setGoodsPrice(request.getGoodsPrice());
        goods.setPartNumber(request.getPartNumber());
        goods.setCategoryNo(request.getCategoryNo());
        goods.setComId(request.getComId());
        goods.setBrandId(request.getBrandId());
        goods.setIsShow(request.getIsShow());
        goods.setRegDm(LocalDateTime.now());
        goods.setRegUserId(request.getRegUserId());
        goods.setUpdDm(LocalDateTime.now());
        goods.setUpdUserId(request.getUpdUserId());

        return goods;
    }

}
