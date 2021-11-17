package goods_crud.goods_api.service;

import goods_crud.goods_api.common.ErrorCode;
import goods_crud.goods_api.domain.Company;
import goods_crud.goods_api.domain.Goods;
import goods_crud.goods_api.dto.CreateGoodsDto;
import goods_crud.goods_api.dto.UpdateGoodsDto;
import goods_crud.goods_api.exception.ApiException;
import goods_crud.goods_api.repository.CompanyRepository;
import goods_crud.goods_api.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsService {
    private final GoodsRepository goodsRepository;

    private final CompanyRepository companyRepository;

    @Transactional
    public Long createGoods(CreateGoodsDto.Request request) {

        Company company1 = Company.builder()
                .comNm("나이스")
                .regUserId("meteo")
                .updUserId("meteo")
                .build();
        companyRepository.save(company1);



        if(StringUtils.isBlank(request.getComId())){
            throw new ApiException(ErrorCode.NOT_EXIST_GOODS_COM_ID);
        }

        Company findCompany = companyRepository.findCompanyByComId(request.getComId());
        Goods goods = Goods.builder().request(request).company(findCompany).build();
        goodsRepository.save(goods);
        return goods.getGoodsNo();
    }

    @Transactional
    public Long updateGoods(Long goodsNo, UpdateGoodsDto.Request request) {
        Company findCompany = null;
        if(StringUtils.isNotBlank(request.getComId())){
            findCompany = companyRepository.findCompanyByComId(request.getComId());
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
}
