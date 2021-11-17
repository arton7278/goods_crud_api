package goods_crud.goods_api.repository;

import goods_crud.goods_api.domain.Company;
import goods_crud.goods_api.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyByComId(String comId);
}
