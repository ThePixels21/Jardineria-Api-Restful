package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.Product;

public interface IProductRepository extends CrudRepository<Product,Long>{
    
}
