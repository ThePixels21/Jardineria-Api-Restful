package jardineria.jardineria.model.service.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jardineria.jardineria.model.entity.Cliente;
import jardineria.jardineria.model.entity.Order;
import jardineria.jardineria.model.repository.IClienteRepository;
import jardineria.jardineria.model.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IClienteRepository clienteRepository; 

    public List<Order> orders = new ArrayList<>();

    @Override
    public ResponseEntity<List<Order>> search() {
        try{
            orders = (List<Order>) orderRepository.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(orders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Order>> save(Order order, Long customerId) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(customerId);
            if (cliente.isPresent()) {
                order.setCustomer(cliente.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Order orderSaved = orderRepository.save(order);

            if(orderSaved!=null) {
                orders.add(orderSaved);
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
    public ResponseEntity<Order> searchById(Long id) {
        try{
            Optional<Order> order = orderRepository.findById(id);
            if(order.isPresent()){
                return new ResponseEntity<>(order.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @Override
    public ResponseEntity<List<Order>> update(Order order,Long clienteId,Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            if(cliente.isPresent()) {
                order.setCustomer(cliente.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<Order> orderSearch = orderRepository.findById(id);
            if(orderSearch.isPresent()) {
                orderSearch.get().setOrderDate(order.getOrderDate());;
                orderSearch.get().setWaitDate(order.getWaitDate());;
                orderSearch.get().setDeliveryDate(order.getDeliveryDate());
                orderSearch.get().setStatus(order.getStatus());;
                orderSearch.get().setComents(order.getComents());
                orderSearch.get().setCustomer(order.getCustomer());

                Order orderSaved = orderRepository.save(orderSearch.get());

                if(orderSaved!=null) {
                    orders.add(orderSaved);
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
    public ResponseEntity<List<Order>> delete(Long id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
