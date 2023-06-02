package jardineria.jardineria.model.service.orderDetails;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jardineria.jardineria.model.entity.Order;
import jardineria.jardineria.model.entity.OrderDetails;
import jardineria.jardineria.model.entity.Product;
import jardineria.jardineria.model.repository.IOrderDetailsRepository;
import jardineria.jardineria.model.repository.IOrderRepository;
import jardineria.jardineria.model.repository.IProductRepository;


@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService {
    @Autowired
    private IOrderDetailsRepository orderDetailRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IOrderRepository orderRepository;

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
            Optional<Order> order = orderRepository.findById(idOrder);
            Optional<Product> product = productRepository.findById(idProduct);
            if (product.isPresent() && order.isPresent()) {
                orderDetail.setProduct(product.get());
                orderDetail.setOrder(order.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            OrderDetails orderSaved = orderDetailRepository.save(orderDetail);

            if(orderSaved!=null) {
                ordersDetails.add(orderSaved);
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
        try{
            Optional<OrderDetails> orderDetails = orderDetailRepository.findById(id);
            if(orderDetails.isPresent()){
                return new ResponseEntity<>(orderDetails.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @Override
    public ResponseEntity<List<OrderDetails>> update(OrderDetails orderDetail, Long orderId,Long productId,Long orderDetailId) {
        try {
            Optional<Order> order = orderRepository.findById(orderId);
            Optional<Product> product = productRepository.findById(productId);
            if(order.isPresent() && product.isPresent()) {
                orderDetail.setOrder(order.get());
                orderDetail.setProduct(product.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<OrderDetails> orderDetailSearch = orderDetailRepository.findById(orderDetailId);
            if(orderDetailSearch.isPresent()) {
                orderDetailSearch.get().setOrder(orderDetail.getOrder());
                orderDetailSearch.get().setProduct(orderDetail.getProduct());
                orderDetailSearch.get().setAmount(orderDetail.getAmount());
                orderDetailSearch.get().setPrice(orderDetail.getPrice());
                orderDetailSearch.get().setLineNumber(orderDetail.getLineNumber());


                OrderDetails orderDetailSaved = orderDetailRepository.save(orderDetailSearch.get());

                if(orderDetailSaved!=null) {
                    ordersDetails.add(orderDetailSaved);
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
    public ResponseEntity<List<OrderDetails>> delete(Long id) {
        try {
            orderDetailRepository.deleteById(id);
            return new ResponseEntity<>(ordersDetails, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
