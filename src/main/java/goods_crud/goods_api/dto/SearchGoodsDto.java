package goods_crud.goods_api.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Slf4j
public class SearchGoodsDto {
	//상품 이름
	private String goodsNm;
	//상품 타입
	private List<String> goodsTypes;
	//성별구분
	private List<String> genderTypes;
	//상품품번
	private String partNumber;
	//카테고리 넘버
	private Integer categoryNo;
	//업체 Id
	private String comId;
	//업체명
	private String comNm;
	// 공개여부
	private Boolean isShow;
	//최소금액
	private Long minPrice;
	//최대 금액
	private Long maxPrice;
	//검색 시작 날짜
	private LocalDateTime startDm;
	//검색 종료 날짜
	private LocalDateTime endDm;
	//정렬 컬럼 디폴트 등록일 (regDm, goodsNm, minPrice, maxPrice)
	private String orderField = "regDm";
	//정렬 타입 디폴트 asc (asc, desc)
	private String orderType = "desc";

	private Integer startNo;

	private Integer endNo;
}
