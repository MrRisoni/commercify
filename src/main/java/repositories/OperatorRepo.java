package repositories;

import entity.rules.RestrictPaymentOperator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepo extends CrudRepository<RestrictPaymentOperator,Long> {
}
