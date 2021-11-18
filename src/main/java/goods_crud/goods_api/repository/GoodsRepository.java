package goods_crud.goods_api.repository;

import goods_crud.goods_api.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsRepositoryCustom{
}
