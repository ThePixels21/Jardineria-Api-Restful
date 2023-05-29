package jardineria.jardineria.model.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import jardineria.jardineria.model.entity.GammaProduct;
import jardineria.jardineria.model.entity.Product;
import jardineria.jardineria.model.repository.IGammaProductRepository;
import jardineria.jardineria.model.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private  IProductRepository productRepository;

    @Autowired
    private IGammaProductRepository gammaRepository;

    List<Product> products = new ArrayList<>();

    @Override
    public ResponseEntity<List<Product>> search() {
        try{
            products = (List<Product>) productRepository.findAll();
            return new ResponseEntity<>(products,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(products,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Product>> save(Product product,Long gammaId) {
        try {
            Optional<GammaProduct> gamma = gammaRepository.findById(gammaId);
            if (gamma.isPresent()) {
                product.setGamma(gamma.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Product productSaved = productRepository.save(product);

            if(productSaved!=null) {
                products.add(productSaved);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> searchById(Long id) {
        try{
            Optional<Product> products = productRepository.findById(id);
            if(products.isPresent()){
                return new ResponseEntity<>(products.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Product>> update(Product product, Long gammaId,Long id) {
        try {

            Optional<GammaProduct> gamma = gammaRepository.findById(gammaId);
            if(gamma.isPresent() ) {
                product.setGamma(gamma.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<Product> productSearch = productRepository.findById(id);
            if(productSearch.isPresent()) {
                productSearch.get().setName(product.getName());
                productSearch.get().setGamma(product.getGamma());
                productSearch.get().setDimentions(product.getDimentions());
                productSearch.get().setSupplier(product.getSupplier());
                productSearch.get().setDescription(product.getDescription());
                productSearch.get().setAmountStock(product.getAmountStock());
                productSearch.get().setPriceSell(product.getPriceSell());
                productSearch.get().setPriceSuppler(product.getPriceSuppler());


                Product productSaved = productRepository.save(productSearch.get());

                if(productSaved!=null) {
                    products.add(productSaved);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
