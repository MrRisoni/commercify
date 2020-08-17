package repositories;

import entity.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends CrudRepository<Products,Long> {
}
