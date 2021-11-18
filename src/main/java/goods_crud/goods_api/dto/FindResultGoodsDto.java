package goods_crud.goods_api.dto;

import com.querydsl.core.annotations.QueryProjection;
import goods_crud.goods_api.domain.Goods;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindResultGoodsDto {
	//상품번호
	private Long goodsNo;
	//상품 이름
	private String goodsNm;
	//상품 설명
	private String goodsCont;
	//상품 타입
	private String goodsType;
	//성별구분
	private String genderType;
	//상품품번
	private String partNumber;
	//카테고리 넘버
	private Integer categoryNo;
	//업체 Id
	private String comId;
	//업체명
	private String comNm;
	//상품 가격
	private Long goodsPrice;
	//할인율
	private Long discountRate;
	//할인 가격
	private Long discountPrice;
	//등록시간
	private LocalDateTime regDm;
	//등록자 Id
	private String regUserId;
	//수정 시간
	private LocalDateTime updDm;
	//수정 Id
	private String updUserId;

	@QueryProjection
	public FindResultGoodsDto(Long goodsNo, String goodsNm, String goodsCont, String goodsType, String genderType, String partNumber, Integer categoryNo,
			String comId, String comNm, Long goodsPrice, Long discountRate, LocalDateTime regDm, String regUserId,
			LocalDateTime updDm, String updUserId) {
		this.goodsNo = goodsNo;
		this.goodsNm = goodsNm;
		this.goodsCont = goodsCont;
		this.goodsType = goodsType;
		this.genderType = genderType;
		this.partNumber = partNumber;
		this.categoryNo = categoryNo;
		this.comId = comId;
		this.comNm = comNm;
		this.goodsPrice = goodsPrice;
		this.discountRate = discountRate;
		if(this.discountRate != null){
			this.discountPrice = Math.round(this.goodsPrice.doubleValue() * (100L - this.discountRate.doubleValue()) / 100.0);
		}
		this.regDm = regDm;
		this.regUserId = regUserId;
		this.updDm = updDm;
		this.updUserId = updUserId;
	}

	@Builder
	public FindResultGoodsDto(Goods findGoods){
		this.goodsNo = findGoods.getGoodsNo();
		this.goodsNm = findGoods.getGoodsNm();
		this.goodsCont = findGoods.getGoodsCont();
		this.goodsType = findGoods.getGoodsType();
		this.genderType = findGoods.getGenderType();
		this.partNumber = findGoods.getPartNumber();
		this.categoryNo = findGoods.getCategoryNo();
		this.comId = findGoods.getCompany().getComId();
		this.comNm = findGoods.getCompany().getComNm();
		this.goodsPrice = findGoods.getGoodsPrice();
		this.discountRate = findGoods.getDiscountRate();
		if(this.discountRate != null){
			this.discountPrice = Math.round(this.goodsPrice.doubleValue() * (100L - this.discountRate.doubleValue()) / 100.0);
		}
		this.regDm = findGoods.getRegDm();
		this.regUserId = findGoods.getRegUserId();
		this.updDm = findGoods.getUpdDm();
		this.updUserId = findGoods.getUpdUserId();

	}
}
