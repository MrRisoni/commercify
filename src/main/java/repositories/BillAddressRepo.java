package repositories;

import entity.order.BillingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillAddressRepo extends CrudRepository<BillingAddress,Long> {
}
