package in.co.project.Ecommerce.controller;

import in.co.project.Ecommerce.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    //create a product
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public void createProduct(Product product) {

    }

    //get product

    public Product getProductById() {

        return null;
    }
    //update product
    public void updateProduct(Product product) {

    }
    //delete product
    public void deleteProduct(Long id) {

    }

}
