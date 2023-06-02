package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.GammaProduct;

public interface IGammaProductRepository extends CrudRepository<GammaProduct,String> {
    
}
