package goods_crud.goods_api.service;

import goods_crud.goods_api.common.ErrorCode;
import goods_crud.goods_api.domain.Company;
import goods_crud.goods_api.domain.Goods;
import goods_crud.goods_api.dto.CreateGoodsDto;
import goods_crud.goods_api.dto.FindResultGoodsDto;
import goods_crud.goods_api.dto.SearchGoodsDto;
import goods_crud.goods_api.dto.UpdateGoodsDto;
import goods_crud.goods_api.exception.ApiException;
import goods_crud.goods_api.repository.CompanyRepository;
import goods_crud.goods_api.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GoodsService {
    private final GoodsRepository goodsRepository;

    private final CompanyRepository companyRepository;

    @Transactional
    public Long createGoods(CreateGoodsDto.Request request) {

        validateDuplicateGoodsNm(request.getGoodsNm(), request.getComId());

        Company findCompany = companyRepository.findCompanyByComId(request.getComId());
//        Company findCompany = companyRepository.findById(request.getComId()).orElse(null);
        Goods goods = Goods.builder().request(request).company(findCompany).build();

        goodsRepository.save(goods);
        return goods.getGoodsNo();
    }

    private void validateDuplicateGoodsNm(String goodsNm, String comId) {
        List<Goods> goods = goodsRepository.findGoodsByGoodsNmAndCompany(goodsNm, comId);
        if(!goods.isEmpty()) {
            throw new ApiException(ErrorCode.DUPLICATE_GOODS_NAME);
        }
    }


    @Transactional
    public Long updateGoods(Long goodsNo, UpdateGoodsDto.Request request) {
        Company findCompany = null;
        if(request.getComId() != null){
            findCompany = companyRepository.findCompanyByComId(request.getComId());
//            findCompany = companyRepository.findById(request.getComId()).orElse(null);
        }


        Goods findGoods = goodsRepository.findById(goodsNo).orElse(null);

        if(findGoods != null){
            findGoods.changeGoods(request, findCompany);
        } else {
            throw new ApiException(ErrorCode.DATA_NOT_GOODS);
        }
        UpdateGoodsDto.Response response = new UpdateGoodsDto.Response();

        return findGoods.getGoodsNo();
    }

	public List<FindResultGoodsDto> findGoodsAll(SearchGoodsDto searchGoodsDto) {
        return goodsRepository.findGoodsAll(searchGoodsDto);
	}

    public FindResultGoodsDto findGoodsOne(Long goodsNo) {
        Goods findGoods = goodsRepository.findById(goodsNo).orElse(null);
        if(findGoods == null) {
            throw new ApiException(ErrorCode.DATA_NOT_GOODS);
        }
        return FindResultGoodsDto.builder().findGoods(findGoods).build();
    }
}
