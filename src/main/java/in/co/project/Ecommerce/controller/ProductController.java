package in.co.project.Ecommerce.controller;

import in.co.project.Ecommerce.dto.FakestoreProductDto;
import in.co.project.Ecommerce.models.Product;
import org.springframework.web.bind.annotation.*;
import in.co.project.Ecommerce.service.FakestoreProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {


    private FakestoreProductService productService; //link b/w controller---> service

    public ProductController(FakestoreProductService productService) { //Dependency Injection
        this.productService = productService;
    }

    //create a product
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        Product p=productService.createProduct(product.getId(),product.getTitle(),product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),product.getImageUrl());
               return p;
    }

    //get product
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        Product product=productService.getSingleProduct(id);
        return product;
    }
    //GET list of products
    @GetMapping("/products")
    public List<Product> getAllProducts(){ // HW
        List<Product> product=productService.getAllProducts();
        System.out.println(product);
        return product;
    }
    //update product
    public void updateProduct( Product product) {

    }
    //delete product
    @DeleteMapping("/products/{id}")
    public void deleteSingleProduct(@PathVariable("id") Long id) { //HW(del EP with void RT)
         productService.deleteSingleProduct(id);

    }

}
