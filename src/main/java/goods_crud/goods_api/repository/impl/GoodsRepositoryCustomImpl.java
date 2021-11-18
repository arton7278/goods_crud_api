package goods_crud.goods_api.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import goods_crud.goods_api.dto.FindResultGoodsDto;
import goods_crud.goods_api.dto.QFindResultGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;
import goods_crud.goods_api.repository.GoodsRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static goods_crud.goods_api.domain.QCompany.company;
import static goods_crud.goods_api.domain.QGoods.goods;

public class GoodsRepositoryCustomImpl implements GoodsRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public GoodsRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


//	@Override public List<FindResultGoodsDto> findGoodsAll(SearchGoodsDto searchGoodsDto) {
//		return queryFactory
//				.select(new QFindResultGoodsDto(
//						goods.
//				))
//				.from(goods)
//				.leftJoin(goods.company, company)
//				.where()
//				.offset(searchGoodsDto.getStartNo())
//				.limit(searchGoodsDto.getEndNo())
//				.fetch();
//	}
}
