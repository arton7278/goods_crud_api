package goods_crud.goods_api.controller;

import goods_crud.goods_api.aspect.GoodsAnnotation;
import goods_crud.goods_api.dto.FindResponseDto;
import goods_crud.goods_api.dto.CreateGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;
import goods_crud.goods_api.dto.UpdateGoodsDto;
import goods_crud.goods_api.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static goods_crud.goods_api.aspect.GoodsAnnotation.*;

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

    @GoodsChangeLogging
    @PutMapping("/api/v1/goods/{goodsNo}")
    public UpdateGoodsDto.Response updateGoods(@PathVariable("goodsNo") Long goodsNo, @RequestBody @Valid UpdateGoodsDto.Request request){
        Long resultGoodsNo = goodsService.updateGoods(goodsNo, request);
        UpdateGoodsDto.Response response = new UpdateGoodsDto.Response();
        return response.toDo(resultGoodsNo);
    }

    @GetMapping("/api/v1/goods")
    public FindResponseDto.GoodsResponse findGoodsAll(@ModelAttribute SearchGoodsDto searchGoodsDto) {
        FindResponseDto.GoodsResponse goodsResponse = new FindResponseDto.GoodsResponse();
        return goodsResponse.toDoList(goodsService.findGoodsAll(searchGoodsDto));
    }

    @GetMapping("/api/v1/goods/{goodsNo}")
    public FindResponseDto.GoodsResponse findGoodsOne(@PathVariable("goodsNo") Long goodsNo) {
        FindResponseDto.GoodsResponse goodsResponse = new FindResponseDto.GoodsResponse();
        return goodsResponse.toDo(goodsService.findGoodsOne(goodsNo));
    }
}
