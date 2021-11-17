package goods_crud.goods_api;

import goods_crud.goods_api.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Comparator;

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
            Company company1 = Company.builder().comNm("킹즈").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company1);

            Company company2 = Company.builder().comNm("하야부사").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company2);
        }

        public void dbInit2() {
            Company company1 = Company.builder().comNm("불테리어").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company1);
            Company company2 = Company.builder().comNm("문디알").regUserId("mateo").updUserId("metao")
                    .build();
            em.persist(company2);
        }
    }
}

