package goods_crud.goods_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class SearchGoodsDto {
	//상품 이름
	private String goodsNm;
	//상품 타입
	private List<String> goodsType;
	//성별구분
	private List<String> genderType;
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
	//정렬 타입 디폴트 desc (asc, desc)
	private String orderType = "desc";

	private Integer startNo = 0;

	private Integer endNo = 10;
}
