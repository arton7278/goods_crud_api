package goods_crud.goods_api.repository;

import goods_crud.goods_api.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsRepositoryCustom{
    List<Goods> findGoodsByGoodsNmAndCompany(String goodsNm, String comId);
}
