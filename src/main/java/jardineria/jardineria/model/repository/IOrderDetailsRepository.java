package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.OrderDetails;

public interface IOrderDetailsRepository extends CrudRepository<OrderDetails,Long> {
    
}
