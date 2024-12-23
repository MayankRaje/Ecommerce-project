package in.co.project.Ecommerce.repository;

import in.co.project.Ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //save
    //get
    //getAll
    //delete
    //update
    Product save(Product product);
    Product findByTitle(String title);
    Product findByName(String name);

}
