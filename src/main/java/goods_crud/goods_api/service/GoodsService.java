package goods_crud.goods_api.service;

import goods_crud.goods_api.domain.Goods;
import goods_crud.goods_api.dto.SaveGoodsDto;
import goods_crud.goods_api.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsService {
    private final GoodsRepository goodsRepository;

    @Transactional
    public Long createGoods(SaveGoodsDto.Request request) {
        Goods goods = Goods.createGoods(request);
        goodsRepository.save(goods);
        return goods.getGoodsNo();
    }

    public Long updateGoods(Long goodsNo, SaveGoodsDto.Request request) {
        Goods findGoods = goodsRepository.getById(goodsNo);

        return findGoods.getGoodsNo();
    }
}
