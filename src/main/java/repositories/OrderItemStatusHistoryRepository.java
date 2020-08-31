package repositories;

import entity.order.OrderItemStatusHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemStatusHistoryRepository extends CrudRepository<OrderItemStatusHistory,Long> {
}
