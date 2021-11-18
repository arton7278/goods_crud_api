package goods_crud.goods_api.controller;

import goods_crud.goods_api.dto.CommonResponseDto;
import goods_crud.goods_api.dto.CreateGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;
import goods_crud.goods_api.dto.UpdateGoodsDto;
import goods_crud.goods_api.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class GoodsApiController {

    private final GoodsService goodsService;

    @PostMapping("/api/v1/goods")
    public CreateGoodsDto.Response createGoods(@RequestBody @Valid CreateGoodsDto.Request request) {
        Long goodsNo = goodsService.createGoods(request);
        CreateGoodsDto.Response response = new CreateGoodsDto.Response();
        return response.toDo(goodsNo);
    }

    @PutMapping("/api/v1/goods/{goodsNo}")
    public UpdateGoodsDto.Response update(@PathVariable("goodsNo") Long goodsNo, @RequestBody UpdateGoodsDto.Request request){
        Long resultGoodsNo = goodsService.updateGoods(goodsNo, request);
        UpdateGoodsDto.Response response = new UpdateGoodsDto.Response();
        return response.toDo(resultGoodsNo);
    }

    @GetMapping("/api/v1/goods")
    public CommonResponseDto findGoodsAll(@ModelAttribute SearchGoodsDto searchGoodsDto) {
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.toDoList(goodsService.findGoodsAll(searchGoodsDto));
        return responseDto;
    }
}
