package repositories;

import entity.rules.RestrictPaymentRules;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictRulesRepo extends CrudRepository<RestrictPaymentRules,Long> {
}
