package in.co.project.Ecommerce.repository;

import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.repository.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
//    //save
//    //get
//    //getAll
//    //delete
//    //update
    Product save(Product product);
    Product findByTitle(String title);
    Product findByDescription(String description);
 //    create query select  * from products
    //Product findByName(String name);

    //HQLct
    @Query("select p from Product p where p.category.id=:categoryId")
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);

    // Implement NativeQuery
    @Query(value = "select * from product p where p.category_id =:categoryId", nativeQuery = true)
    List<Product> getProductByCategoryIdNativeQueries(@Param("categoryId") Long categoryId);

    // for projection
    @Query("select p.id as id ,p.title as title from Product p where p.category.id=:categoryId")
    List<ProductProjection> getProductsByCategoryIdProjections(@Param("categoryId") Long categoryId);

}
