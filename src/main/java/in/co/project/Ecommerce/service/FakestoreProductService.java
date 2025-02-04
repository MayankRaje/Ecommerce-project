package in.co.project.Ecommerce.service;

import in.co.project.Ecommerce.Exceptions.ProductNotFoundException;
import in.co.project.Ecommerce.dto.FakestoreProductDto;
import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakestoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private RedisTemplate <String,Object> redisTemplate;

    public FakestoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    //    public FakestoreProductService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }


//    public FakestoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
//        this.restTemplate = restTemplate;
//        this.redisTemplate = redisTemplate;
//    }


    @Override
    public Product getSingleProduct(Long id)throws ProductNotFoundException {
        //Implement Redis

        // First part is : assume it as table name
        // Second part : key of the product

        Product redisProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_"+id);

        if(redisProduct != null) {
            // cache hit
            System.out.println("Inside redis");
            return redisProduct;
        }


        FakestoreProductDto fakestoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakestoreProductDto.class);
        //System.out.println("returning single product method");
        if(fakestoreProductDto==null){
            throw new ProductNotFoundException("product not found with id : "+id);
        }

        // convert FakestoreProductDtO to my Product

//        return fakestoreProductDto.getProduct();

        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_"+id, fakestoreProductDto.getProduct());

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
    public Page<Product> getAllProductsbyPageNumber(int pageNumber, int pageSize, String fieldName) {
        return null;
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

    @Override
    public Product updateProduct(Long id, String title, Double price, String description, String image, String category) throws ProductNotFoundException {
        Product product=getSingleProduct(id);
        FakestoreProductDto fakestoreProductDto=new FakestoreProductDto();
        fakestoreProductDto.setId(product.getId());
        fakestoreProductDto.setTitle(product.getTitle());
        fakestoreProductDto.setPrice(product.getPrice());
        fakestoreProductDto.setDescription(product.getDescription());
        fakestoreProductDto.setCategory(product.getCategory().getTitle());
        fakestoreProductDto.setImage(image);

        restTemplate.put("https://fakestoreapi.com/products/"+id,fakestoreProductDto
                ,FakestoreProductDto.class);
//        HttpHeaders headers=new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Product>entity=new HttpEntity<>(product,headers);
//        restTemplate.exchange("https://fakestoreapi.com/products/"+id, HttpMethod.PUT,entity
//                ,Product.class);
//
//
        return fakestoreProductDto.getProduct();
    }

//    @Override
//    public Product updateProduct(Long id,Product product) {
//        Product product=getSingleProduct(id);
//        FakestoreProductDto fakestoreProductDto=new FakestoreProductDto();
//        fakestoreProductDto.setId(product.getId());
//        fakestoreProductDto.setTitle(product.getTitle());
//        fakestoreProductDto.setPrice(product.getPrice());
//        fakestoreProductDto.setDescription(product.getDescription());
//        fakestoreProductDto.setCategory(product.getCategory().getTitle());
//        fakestoreProductDto.setImage(image);
//
//        return fakestoreProductDto.getProduct();
//    }

//    @Override
//    public Product createProduct(@RequestBody Product product) {
//        return null;
//    }

//      @Override
//      public void deleteSingleProduct(Long id) { //1 VOID RETURN TYPE
//        //M==>1 ==> Itetate ans skip an id
//        //M==>2
//        restTemplate.delete("https://fakestoreapi.com/products/"+ id,id);
//
//      }

    @Override
    public Product deleteSingleProduct(Long id) throws ProductNotFoundException { //RETURN PRODUCT IN POSTMAN
        //M==>1 ==> Itetate ans skip an id
        //M==>2
        Product product = getSingleProduct(id);
        restTemplate.delete("https://fakestoreapi.com/products/"+ id,id);
        return product;
    }

    //@Override
//    public Product deleteSingleProduct(Product product1) { //implement via response body from POSTMAN
//        //M==>1 ==> Itetate ans skip an id
//        //M==>2
//        Long id=product1.getId();
//        Product product=getSingleProduct(product1.getId());
//        restTemplate.delete("https://fakestoreapi.com/products/"+ id,id);
//        return product;
//    }

}
