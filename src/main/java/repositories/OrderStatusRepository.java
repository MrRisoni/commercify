package repositories;

import entity.order.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus,Long> {
    OrderStatus findOneByTitle(String pending);
}
