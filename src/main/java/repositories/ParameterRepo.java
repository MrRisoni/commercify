package repositories;

import entity.rules.RestrictPaymentParameters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepo  extends CrudRepository<RestrictPaymentParameters,Long> {
}
