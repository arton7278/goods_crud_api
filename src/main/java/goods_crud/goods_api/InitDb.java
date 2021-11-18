package goods_crud.goods_api;

import goods_crud.goods_api.domain.Company;
import goods_crud.goods_api.domain.Goods;
import goods_crud.goods_api.dto.CreateGoodsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 종 주문 2개
 * * userA
 * 	 * JPA1 BOOK
 * 	 * JPA2 BOOK
 * * userB
 * 	 * SPRING1 BOOK
 * 	 * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Company company1 = Company.builder().comId("kinz").comNm("킹즈").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company1);

            Company company2 = Company.builder().comId("habs").comNm("하야부사").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company2);

            CreateGoodsDto.Request request1 = new CreateGoodsDto.Request();
            request1.setGoodsNm("킹즈발리스티코");
            request1.setGoodsPrice(200000L);
            request1.setGenderType("man");
            request1.setComId("kinz");
            request1.setGoodsCont("경량도복");
            request1.setCategoryNo(100);
            Goods goods1 = Goods.builder().request(request1).company(company1).build();
            em.persist(goods1);

            CreateGoodsDto.Request request2 = new CreateGoodsDto.Request();
            request2.setGoodsNm("킹즈나노");
            request2.setGoodsPrice(180000L);
            request2.setGenderType("man");
            request2.setComId("kinz");
            request2.setGoodsCont("경량도복");
            request2.setCategoryNo(100);
            Goods goods2 = Goods.builder().request(request2).company(company1).build();
            em.persist(goods2);


            CreateGoodsDto.Request request3 = new CreateGoodsDto.Request();
            request3.setGoodsNm("하야부사라이트");
            request3.setGoodsPrice(180000L);
            request3.setGenderType("man");
            request3.setComId("habs");
            request3.setGoodsCont("경량도복");
            request3.setCategoryNo(100);
            Goods goods3 = Goods.builder().request(request3).company(company2).build();
            em.persist(goods3);


        }

        public void dbInit2() {
            Company company1 = Company.builder().comId("adidas").comNm("아디다스").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company1);

            CreateGoodsDto.Request request1 = new CreateGoodsDto.Request();
            request1.setGoodsNm("오즈위고");
            request1.setGoodsPrice(129000L);
            request1.setDiscountRate(59L);
            request1.setGenderType("unisex");
            request1.setComId("adidas");
            request1.setGoodsCont("블랙");
            request1.setCategoryNo(200);
            Goods goods1 = Goods.builder().request(request1).company(company1).build();
            em.persist(goods1);
        }
    }
}

