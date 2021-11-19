package goods_crud.goods_api.repository;

import goods_crud.goods_api.domain.Company;
import goods_crud.goods_api.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsRepositoryCustom{
    @Query("SELECT count(g) FROM Goods g LEFT OUTER JOIN g.company c " +
            "WHERE g.goodsNm = :goodsNm and c.comId = :comId")
    Long findGoodsByGoodsNmAndCompany(@Param("goodsNm") String goodsNm, @Param("comId") String comId);
}
