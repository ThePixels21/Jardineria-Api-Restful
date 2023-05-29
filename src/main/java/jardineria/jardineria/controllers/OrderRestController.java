package jardineria.jardineria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jardineria.jardineria.model.entity.Order;
import jardineria.jardineria.model.service.Order.IOrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class OrderRestController {
    
    private IOrderService orderService;

    @GetMapping("orders")
    public ResponseEntity<List<Order>> searchOficinas(){
        return orderService.search();
    }

    @PostMapping("orders")
    public ResponseEntity<List<Order>> saveOficina(@RequestParam("order") Order order,@RequestParam("customerId") Long customerId){
        return orderService.save(order, customerId);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> searchOficinaById(@PathVariable Long id) {
        return orderService.searchById(id);
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<List<Order>> updateOficina(@RequestParam("order") Order order, @PathVariable Long id,@RequestParam("clienteId") Long clienteId) {
        return orderService.update(order, id, clienteId);
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<List<Order>> deleteOficina(@PathVariable Long id){
        return orderService.delete(id);
    }
}
