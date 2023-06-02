package jardineria.jardineria.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ResponseEntity<List<Order>> saveOficina(@RequestParam("waitDate") String waitDate,String deliveryDate,
    @RequestParam("status") String status,String comments,@RequestParam("customerId") Long customerId) throws Exception{

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date aux1 = dateFormat.parse(waitDate);

        Order order = new Order();

        order.setOrderDate(new Date());
        order.setWaitDate(aux1);

        if(deliveryDate == null){
            order.setDeliveryDate(null);
        }else{
            Date aux2 = dateFormat.parse(deliveryDate);
            order.setDeliveryDate(aux2);
        }
        order.setStatus(status);
        order.setComents(comments);
        
        
        return orderService.save(order, customerId);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> searchOficinaById(@PathVariable Long id) {
        return orderService.searchById(id);
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<List<Order>> updateOficina(@RequestParam("waitDate") String waitDate,String deliveryDate,
    @RequestParam("status") String status,String comments,@RequestParam("customerId") Long customerId,
    @PathVariable Long id) throws Exception {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date aux1 = dateFormat.parse(waitDate);
        
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setWaitDate(aux1);

        if(deliveryDate == null){
            order.setDeliveryDate(null);
        }else{
            Date aux2 = dateFormat.parse(deliveryDate);
            order.setDeliveryDate(aux2);
        }
        order.setStatus(status);
        order.setComents(comments);
        
        
        return orderService.update(order, customerId, id);
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<List<Order>> deleteOficina(@PathVariable Long id){
        return orderService.delete(id);
    }
}
