package goods_crud.goods_api.Controller;

import goods_crud.goods_api.dto.SaveGoodsDto;
import goods_crud.goods_api.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GoodsApiController {
    private final GoodsService goodsService;

    @PostMapping("/api/v1/goods")
    public SaveGoodsDto.Response createGoods(@RequestBody SaveGoodsDto.Request request) {
        Long goodsNo = goodsService.createGoods(request);
        SaveGoodsDto.Response response = new SaveGoodsDto.Response();
        return response.toDo(goodsNo)
    }

    @PutMapping("/api/v1/goods/{goodsNo}")
    public SaveGoodsDto.Response update(@PathVariable("goodsNo") Long goodsNo, @RequestBody SaveGoodsDto.Request request){
        Long resultGoodsNo = goodsService.updateGoods(goodsNo, request);
        SaveGoodsDto.Response response = new SaveGoodsDto.Response();
        return response.toDo(resultGoodsNo);
    }
}
