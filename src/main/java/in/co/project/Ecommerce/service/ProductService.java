package in.co.project.Ecommerce.service;
import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    //Product createProduct(Product product);
    Product createProduct(Long id, String title, String description, Double price, String category, String image);


}
