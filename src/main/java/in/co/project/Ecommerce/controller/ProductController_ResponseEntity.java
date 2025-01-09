package in.co.project.Ecommerce.controller;

import in.co.project.Ecommerce.Exceptions.ProductNotFoundException;
import in.co.project.Ecommerce.dto.ErrorDto;
import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.service.FakestoreProductService;
import in.co.project.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
@Primary
@RequestMapping("/responseEntity")
public class ProductController_ResponseEntity {

    private FakestoreProductService productService;
    
    //private ProductService productService;
    @Autowired
    public void setProductService(FakestoreProductService productService) {//setter DI
        this.productService = productService;
    }
//    public void setProductService(@Qualifier("selfProductService") ProductService productService) {//setter DI
//        this.productService = productService;
//    }

    //create
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product)throws ProductNotFoundException {
        Product p=productService.createProduct(product.getId(),product.getTitle(),product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),product.getImageUrl());
        ResponseEntity<Product> response = new ResponseEntity<>(p, HttpStatus.OK);
        return response;
    }
    //get product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {
        //System.out.println("Starting the api here");
        Product p = productService.getSingleProduct(id);
        //System.out.println("Ending the api here");
        ResponseEntity<Product> response = new ResponseEntity<>(p, HttpStatus.OK);
        return response;
    }

    //getAll
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()throws ProductNotFoundException{ // HW
        List<Product> product=productService.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(product, HttpStatus.OK);
//        System.out.println(product);
        return response;
    }

    //update product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product)throws ProductNotFoundException {
        Product p = productService.updateProduct(id,product.getTitle(),product.getPrice(),
                product.getDescription(),product.getImageUrl(),
                product.getCategory().getTitle());// we can directly pass Product object here instead of properties

        ResponseEntity<Product> response = new ResponseEntity<>(p, HttpStatus.OK);
        return response;
    }

    //delete
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteSingleProduct(@PathVariable("id") Long id)throws ProductNotFoundException { //HW(del EP with product RT)
        Product product=productService.deleteSingleProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());


        ResponseEntity<ErrorDto> response = new ResponseEntity<>(
                errorDto, HttpStatus.NOT_FOUND
        );
        return response;
    }




}
