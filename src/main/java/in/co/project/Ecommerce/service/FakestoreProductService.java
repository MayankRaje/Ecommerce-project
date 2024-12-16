package in.co.project.Ecommerce.service;

import in.co.project.Ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakestoreProductService implements ProductService{
    @Override
    public Product getSingleProduct(Long id) {
        System.out.println("returning single product method");
        return null;
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
