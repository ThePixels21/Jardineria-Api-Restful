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

import jardineria.jardineria.model.entity.OrderDetails;
import jardineria.jardineria.model.service.orderDetails.IOrderDetailsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class OrderDetailsRestController {

    public IOrderDetailsService orderDetailService;

    @GetMapping("orderDetails")
    public ResponseEntity<List<OrderDetails>> getAllProducts() {
        return orderDetailService.search();
    }
    
    @PostMapping("orderDetails")
    public ResponseEntity<List<OrderDetails>> saveProduct(@RequestParam("orderId") Long orderId,
    @RequestParam("productId") Long productId , @RequestParam("amount") int amount, 
    @RequestParam("price") double price,
    @RequestParam("lineNumber") double lineNumber) throws Exception {
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setAmount(amount);
        orderDetail.setPrice(price);
        orderDetail.setLineNumber(amount);
        return orderDetailService.save(orderDetail, orderId, productId);
    }

    @PutMapping("orderDetails/{id}")
    public ResponseEntity<List<OrderDetails>> updateProduct(@RequestParam("orderId") Long orderId,@RequestParam("productId") Long productId , @RequestParam("amount") int amount, 
    @RequestParam("price") double price,@RequestParam("lineNumber") double lineNumber,@PathVariable Long id) throws Exception {
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setAmount(amount);
        orderDetail.setPrice(price);
        orderDetail.setLineNumber(amount);
        return orderDetailService.update(orderDetail, orderId, productId,id);
    }

    @GetMapping("orderDetails/{id}")
    public ResponseEntity<OrderDetails> searchProductById(@PathVariable Long id) {
        return orderDetailService.searchById(id);
    }

    @DeleteMapping("orderDetails/{id}")
    @Transactional
    public ResponseEntity<List<OrderDetails>> deleteProduct(@PathVariable Long id) {
        return orderDetailService.delete(id);
    }
}
