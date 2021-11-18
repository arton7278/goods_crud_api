package goods_crud.goods_api.domain;

import goods_crud.goods_api.dto.CreateGoodsDto;
import goods_crud.goods_api.dto.UpdateGoodsDto;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {
    //goods 시리얼
    @Id @GeneratedValue
    private Long goodsNo;
    //상품 이름
    @Column(nullable = false)
    private String goodsNm;
    //상품 설명
    private String goodsCont;
    //상품 타입
    private String goodsType;
    //상품가격
    @Column(nullable = false)
    private Long goodsPrice;
    //상품 할인율
    private Long discountRate;
    //성별구분
    @Column(nullable = false)
    private String genderType;
    //상품품번
    private String partNumber;
    //카테고리 넘버
    @Column(nullable = false)
    private Integer categoryNo;
    //업체 Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comId")
    private Company company;
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

    @Builder
    public Goods (CreateGoodsDto.Request request, Company company) {
        this.goodsNm = request.getGoodsNm();
        this.goodsPrice = request.getGoodsPrice();
        this.categoryNo = request.getCategoryNo();
        this.company = company;
        this.genderType = request.getGenderType();

        this.discountRate = request.getDiscountRate();
        this.goodsCont = request.getGoodsCont();
        this.goodsType = request.getGoodsType();
        this.partNumber = request.getPartNumber();

        this.isShow = request.getIsShow();
        this.regDm = LocalDateTime.now();
        this.regUserId = request.getRegUserId();
        this.updDm = LocalDateTime.now();
        this.updUserId = request.getUpdUserId();
    }

    public void changeGoods(UpdateGoodsDto.Request request, Company company){

        if(StringUtils.isNotBlank(request.getGoodsNm())){
            this.goodsNm = request.getGoodsNm();
        }

        //테스트
        this.goodsCont = request.getGoodsCont();

        if(StringUtils.isNotBlank(request.getGoodsType())){
            this.goodsType = request.getGoodsType();
        }

        if(request.getGoodsPrice() != null && request.getGoodsPrice() > 0) {
            this.goodsPrice = request.getGoodsPrice();
        }

        if(request.getDiscountRate() != null){
            this.discountRate = request.getDiscountRate();
        }

        if(request.getPartNumber() != null ){
            this.partNumber = request.getPartNumber();
        }

        if(request.getCategoryNo() != null){
            this.categoryNo = request.getCategoryNo();
        }

        if(company != null){
            this.company = company;
        }

        this.isShow = request.getIsShow();
        this.updDm = LocalDateTime.now();
        this.updUserId = request.getUpdUserId();
    }

}
