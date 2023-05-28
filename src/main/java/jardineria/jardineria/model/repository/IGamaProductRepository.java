package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.GamaProduct;

public interface IGamaProductRepository extends CrudRepository<GamaProduct,Long> {
    
}
