package in.co.project.Ecommerce;

import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.repository.ProductRepository;
import in.co.project.Ecommerce.repository.projections.ProductProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServicesApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }
//    @Test
//    void testQueries(){
//        List<Product> p=productRepository.getProductsByCategoryId(1L);
//        for(Product p1:p) {
//            System.out.println(p1);
//        }

    @Test
    void testQueries(){
        List<ProductProjection> p=productRepository.getProductsByCategoryIdProjections(1L);
        for(ProductProjection p1:p) {
            System.out.println(p1.getTitle());
        }
    }

}
