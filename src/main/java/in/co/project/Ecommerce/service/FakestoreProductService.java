package in.co.project.Ecommerce.service;

import in.co.project.Ecommerce.dto.FakestoreProductDto;
import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Product> getAllProducts() {//HW
        FakestoreProductDto[] fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products",
                        FakestoreProductDto[].class);
        System.out.println("From Products service");
        List<Product> product = new ArrayList<>();
       // Product[] products = new Product[fakeStoreProductDto.length];
        for(int i=0;i<fakeStoreProductDto.length;i++){
            product.add(fakeStoreProductDto[i].getProduct());
        }
//        int c=0;
//        for(FakestoreProductDto i:fakeStoreProductDto){
//            product.add(fakeStoreProductDto[c++].getProduct());
//        }
        return product;
    }

    @Override
    public Product createProduct(Long id, String title, String description, Double price, String category, String image) {
        FakestoreProductDto fakestoreProductDto=new FakestoreProductDto();
        fakestoreProductDto.setId(id);
        fakestoreProductDto.setTitle(title);
        fakestoreProductDto.setPrice(price);
        fakestoreProductDto.setDescription(description);
        fakestoreProductDto.setCategory(category);
        fakestoreProductDto.setImage(image);

        FakestoreProductDto response=restTemplate.postForObject("https://fakestoreapi.com/products",
                                                                fakestoreProductDto,FakestoreProductDto.class);
        return response.getProduct();
    }

//    @Override

//    public Product updateProduct(Long id, String title, Double price, String description, String image, String category) {
//        FakestoreProductDto fakestoreProductDto=new FakestoreProductDto();
//        fakestoreProductDto.setId(id);
//        fakestoreProductDto.setTitle(title);
//        fakestoreProductDto.setPrice(price);
//        fakestoreProductDto.setDescription(description);
//        fakestoreProductDto.setCategory(category);
//        fakestoreProductDto.setImage(image);
//
//        FakestoreProductDto response=restTemplate.exc("https://fakestoreapi.com/products",
//                fakestoreProductDto,FakestoreProductDto.class);
//        return response.getProduct();
//
//
//        return null;
//    }

//    @Override

//    public Product createProduct(@RequestBody Product product) {
//        return null;
//    }

      public void deleteSingleProduct(Long id) {
        //M==>1 ==> Itetate ans skip an id
        //M==>2
        restTemplate.delete("https://fakestoreapi.com/products/"+ id,id);

      }


}
