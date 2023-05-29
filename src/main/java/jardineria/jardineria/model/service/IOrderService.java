package jardineria.jardineria.model.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.Order;

public interface IOrderService {

    public ResponseEntity<List<Order>> search();

    public ResponseEntity<List<Order>> save(Order order,Long customerId);

    public ResponseEntity<Order> searchById(Long id);

    public ResponseEntity<List<Order>> update(Order order,Long id);

    public ResponseEntity<List<Order>> delete(Long id);
}
