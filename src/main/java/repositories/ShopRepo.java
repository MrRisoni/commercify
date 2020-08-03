package repositories;

import entities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends CrudRepository<Shop,Long> {
}
