package goods_crud.goods_api.repository.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import goods_crud.goods_api.dto.FindResultGoodsDto;
import goods_crud.goods_api.dto.QFindResultGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;
import goods_crud.goods_api.repository.GoodsRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static goods_crud.goods_api.domain.QCompany.company;
import static goods_crud.goods_api.domain.QGoods.goods;


public class GoodsRepositoryCustomImpl implements GoodsRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public GoodsRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


	@Override public List<FindResultGoodsDto> findGoodsAll(SearchGoodsDto searchGoodsDto) {
		return queryFactory
				.select(new QFindResultGoodsDto(
					goods.goodsNo,
					goods.goodsNm,
					goods.goodsCont,
					goods.goodsType,
					goods.genderType,
					goods.partNumber,
					goods.categoryNo,
					company.comId,
					company.comNm,
					goods.goodsPrice,
					goods.discountRate,
					goods.regDm,
					goods.regUserId,
					goods.updDm,
					goods.updUserId
				))
				.from(goods)
				.leftJoin(goods.company, company)
				.where( goodsNmContains(searchGoodsDto.getGoodsNm()),
						comNmContains(searchGoodsDto.getComNm()),
						goodsTypeIn(searchGoodsDto.getGoodsTypes()),
						goodsGenderIn(searchGoodsDto.getGenderTypes()),
						isShowIn(searchGoodsDto.getIsShow()),
						partNumberEq(searchGoodsDto.getPartNumber()),
						categoryNoEq(searchGoodsDto.getCategoryNo()),
						comIdEq(searchGoodsDto.getComId()),
						comNmContains(searchGoodsDto.getComNm()),
						minPriceGoe(searchGoodsDto.getMinPrice()),
						maxPriceLoe(searchGoodsDto.getMaxPrice()),
						dateBetween(searchGoodsDto.getStartDm(), searchGoodsDto.getEndDm()))
				.orderBy(goods.regDm.desc())
				.offset((searchGoodsDto.getStartNo() != null ? searchGoodsDto.getStartNo() : 0))
				.limit((searchGoodsDto.getEndNo() != null ? searchGoodsDto.getEndNo(): 10))
				.fetch();
	}

	private BooleanExpression goodsNmContains(String goodsNm) { return StringUtils.isNotBlank(goodsNm) ? goods.goodsNm.contains(goodsNm) : null; }
	private BooleanExpression comNmContains(String comNm) { return StringUtils.isNotBlank(comNm) ? company.comNm.contains(comNm) : null; }

	private BooleanExpression goodsTypeIn(List<String> goodsTypes) { return (goodsTypes != null && !goodsTypes.isEmpty()) ? goods.goodsType.in(goodsTypes) : null; }
	private BooleanExpression goodsGenderIn(List<String> genderTypes) { return (genderTypes != null && !genderTypes.isEmpty()) ? goods.genderType.in(genderTypes) : null; }
	private BooleanExpression isShowIn(Boolean isShow) { return (isShow != null) ? goods.isShow.in(isShow) : null; }

	private BooleanExpression partNumberEq(String partNumber) { return StringUtils.isNotBlank(partNumber) ? goods.partNumber.eq(partNumber) : null; }
	private BooleanExpression categoryNoEq(Integer categoryNo) { return categoryNo != null ? goods.categoryNo.eq(Integer.valueOf(categoryNo)) : null; }
	private BooleanExpression comIdEq(String comId) { return StringUtils.isNotBlank(comId) ? company.comId.eq(comId) : null; }

	private BooleanExpression minPriceGoe(Long minPrice) { return minPrice != null ? goods.goodsPrice.goe(minPrice) : null; }
	private BooleanExpression maxPriceLoe(Long maxPrice) { return maxPrice != null ? goods.goodsPrice.loe(maxPrice) : null; }

	private BooleanExpression dateBetween(LocalDateTime startDm, LocalDateTime endDm) {
		return (startDm != null && endDm != null) ? goods.regDm.between(startDm, endDm) : null;
	}



}
