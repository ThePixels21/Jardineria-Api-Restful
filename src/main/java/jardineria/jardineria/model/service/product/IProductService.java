package jardineria.jardineria.model.service.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.GammaProduct;
import jardineria.jardineria.model.entity.Product;

public interface IProductService {
    
    public ResponseEntity<List<Product>> search();

    public ResponseEntity<List<Product>> save(Product product,Long gamma);

    public ResponseEntity<Product> searchById(Long id);

    public ResponseEntity<List<Product>> update(Product product,Long id);

    public ResponseEntity<List<Product>> delete(Long id);
}
