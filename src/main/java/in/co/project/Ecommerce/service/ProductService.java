package in.co.project.Ecommerce.service;
import in.co.project.Ecommerce.Exceptions.ProductNotFoundException;
import in.co.project.Ecommerce.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id)throws ProductNotFoundException;
    List<Product> getAllProducts();
    //by page number
    Page<Product> getAllProductsbyPageNumber(int pageNumber, int pageSize, String fieldName);
    //Product createProduct(Product product);
    //void deleteSingleProduct(Long id);//1
    Product deleteSingleProduct(Long id) throws ProductNotFoundException;//2
    //Product deleteSingleProduct(Product product);//3

    Product createProduct(Long id, String title, String description, Double price, String category, String image);//service is independent of type to product data
    Product updateProduct(Long id, String title, Double price, String description, String image, String category) throws ProductNotFoundException;


}
