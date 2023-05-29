package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.Order;

public interface IOrderRepository extends CrudRepository<Order,Long> {
    
}
