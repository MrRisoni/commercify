package repositories;

import entity.order.OrderStatusHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusHistoryRepository extends CrudRepository<OrderStatusHistory,Long> {
}
