package in.co.project.Ecommerce.controller;

import in.co.project.Ecommerce.models.Product;
import org.springframework.web.bind.annotation.*;
import in.co.project.Ecommerce.service.FakestoreProductService;

@RestController
public class ProductController {


    private FakestoreProductService productService; //link b/w controller---> service

    public ProductController(FakestoreProductService productService) { //Dependency Injection
        this.productService = productService;
    }

    //create a product
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public void createProduct(Product product) {

    }

    //get product
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        productService.getSingleProduct(id);
        return null;
    }
    //update product
    public void updateProduct(Product product) {

    }
    //delete product
    public void deleteProduct(Long id) {

    }

}
