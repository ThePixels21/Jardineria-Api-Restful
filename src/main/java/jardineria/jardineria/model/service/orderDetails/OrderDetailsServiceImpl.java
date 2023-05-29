package jardineria.jardineria.model.service.orderDetails;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jardineria.jardineria.model.entity.OrderDetails;
import jardineria.jardineria.model.entity.Product;
import jardineria.jardineria.model.repository.IOrderDetailsRepository;
import jardineria.jardineria.model.repository.IProductRepository;


@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService {
    @Autowired
    private IOrderDetailsRepository orderDetailRepository;

    @Autowired
    private IProductRepository productRepository;

    List<OrderDetails> ordersDetails = new ArrayList<>();

    @Override
    public ResponseEntity<List<OrderDetails>> search() {
        try{
            ordersDetails = (List<OrderDetails>) orderDetailRepository.findAll();
            return new ResponseEntity<>(ordersDetails,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(ordersDetails,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetails>> save(OrderDetails orderDetail, Long idOrder, Long idProduct) {
        try {
            Optional<Order> order = orderRepository.findById(idOrder)
            Optional<Product> product = productRepository.findById(idProduct);
            if (product.isPresent() && order.isPresent()) {
                product.setGamma(gamma.get());
                orderDetail.setProduct(product.get());
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
    public ResponseEntity<OrderDetails> searchById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchById'");
    }

    @Override
    public ResponseEntity<List<OrderDetails>> update(OrderDetails order, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ResponseEntity<List<OrderDetails>> delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
