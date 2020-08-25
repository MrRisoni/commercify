package repositories;

import entity.product.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends CrudRepository<Products,Long> {
}
