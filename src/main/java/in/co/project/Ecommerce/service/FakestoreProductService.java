package in.co.project.Ecommerce.service;

import in.co.project.Ecommerce.dto.FakestoreProductDto;
import in.co.project.Ecommerce.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakestoreProductService implements ProductService{
    RestTemplate restTemplate;

    public FakestoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakestoreProductDto fakestoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/1"+id, FakestoreProductDto.class);
        //System.out.println("returning single product method");
        // convert FakestoreProductDtO to my Product

        return fakestoreProductDto.getProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
