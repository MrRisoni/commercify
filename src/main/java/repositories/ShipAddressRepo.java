package repositories;

import entity.order.ShippingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipAddressRepo  extends CrudRepository<ShippingAddress,Long> {
}
