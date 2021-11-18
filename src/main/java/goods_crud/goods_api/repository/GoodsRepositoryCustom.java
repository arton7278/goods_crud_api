package goods_crud.goods_api.repository;

import goods_crud.goods_api.dto.FindResultGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;

import java.util.List;

public interface GoodsRepositoryCustom {
	List<FindResultGoodsDto> findGoodsAll(SearchGoodsDto searchGoodsDto);
}
