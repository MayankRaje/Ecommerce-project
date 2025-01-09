package in.co.project.Ecommerce.controller;

import in.co.project.Ecommerce.Exceptions.ProductNotFoundException;
import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.service.ProductService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selfProduct")
public class ProductController_SelfproductService {

    // CRUD apis around product
    // For the product
    // 1. to create a product
    // 2. get a product
    // 3. update a product
    // 4. delete a product

    private ProductService productService;

    public ProductController_SelfproductService(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    // This will help in creating the product
//    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        Product p = productService.createProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getPrice(), product.getCategory().getTitle(),product.getImageUrl());

        return p;
    }

    // This will help in getting the product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Starting the api here");
        Product p = productService.getSingleProduct(id);
        System.out.println("Ending the api here");

        ResponseEntity<Product> response = new ResponseEntity<>(
                p, HttpStatus.OK
        );

        return response;
    }

    public void updateProduct(Product product) {

    }
    @DeleteMapping("products/{id}")
    public void deleteProduct(@PathVariable("id")Long id) throws ProductNotFoundException {
        productService.deleteSingleProduct(id);

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()throws ProductNotFoundException { // HW
        List<Product> product = productService.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(product, HttpStatus.OK);
//        System.out.println(product);
        return response;
    }

    // pageSize, pageNumber, fieldName, sort Order
//    @GetMapping("/products")
//    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize")int pageSize, @RequestParam("fieldName")String fieldName, @RequestParam("searchQuery") String searchQuery) {
//        return productService.getAllProducts(pageNumber,pageSize, fieldName);
//    }







}
