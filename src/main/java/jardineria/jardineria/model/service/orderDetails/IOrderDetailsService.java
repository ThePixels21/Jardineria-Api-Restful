package jardineria.jardineria.model.service.orderDetails;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.OrderDetails;

public interface IOrderDetailsService  {
    
    public ResponseEntity<List<OrderDetails>> search();

    public ResponseEntity<List<OrderDetails>> save(OrderDetails Order,Long idPedido,Long idProducto);

    public ResponseEntity<OrderDetails> searchById(Long id);

    public ResponseEntity<List<OrderDetails>> update(OrderDetails orderDetail,Long orderId,Long productId,Long orderDetailId);

    public ResponseEntity<List<OrderDetails>> delete(Long id);
}
