package jardineria.jardineria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jardineria.jardineria.model.entity.Product;
import jardineria.jardineria.model.service.product.IProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProductRestController {
    
    public IProductService productService;

    @GetMapping("products")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.search();
    }
    
    @PostMapping("products")
    @Transactional
    public ResponseEntity<List<Product>> saveProduct(@RequestParam("name") String name,@RequestParam("gammaId") String gammaId , String dimentions, 
    String supplier, String description,@RequestParam("amountStock") int amountStock, @RequestParam("priceSell") double priceSell,
    double priceSupplier) throws Exception {
        Product product = new Product();
        product.setName(name);
        product.setDimentions(dimentions);
        product.setSupplier(supplier);
        product.setDescription(description);
        product.setAmountStock(amountStock);
        product.setPriceSell(priceSell);
        product.setPriceSuppler(priceSupplier);
        return productService.save(product,gammaId);
    }

    @PutMapping("products/{id}")
    @Transactional
    public ResponseEntity<List<Product>> updateProduct(@RequestParam("name") String name,@RequestParam("gammaId") String gammaId , String dimentions, 
    String supplier, String description,@RequestParam("amountStock") int amountStock,
    @RequestParam("priceSell") double priceSell,double priceSupplier,@PathVariable Long id) throws Exception {
        Product product = new Product();
        product.setName(name);
        product.setDimentions(dimentions);
        product.setSupplier(supplier);
        product.setDescription(description);
        product.setAmountStock(amountStock);
        product.setPriceSell(priceSell);
        product.setPriceSuppler(priceSupplier);
        return productService.update(product, gammaId,id);
    }

    @GetMapping("products/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Product> searchProductById(@PathVariable Long id) {
        return productService.searchById(id);
    }

    @DeleteMapping("products/{id}")
    @Transactional
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }
}
