package in.co.project.Ecommerce.service;
import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    //Product createProduct(Product product);
    //void deleteSingleProduct(Long id);//1
    Product deleteSingleProduct(Long id);//2
    //Product deleteSingleProduct(Product product);//3

    Product createProduct(Long id, String title, String description, Double price, String category, String image);
    Product updateProduct(Long id, String title, Double price, String description, String image, String category);



}
