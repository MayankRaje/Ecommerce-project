package in.co.project.Ecommerce.service;

import in.co.project.Ecommerce.Exceptions.ProductNotFoundException;
import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.repository.CategoryRepository;
import in.co.project.Ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    //private RedisTemplate redisTemplate;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }
    //    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {//DI
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        //Product redisProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_"+id);

//        if(redisProduct != null) {
//            // cache hit
//            System.out.println("Inside redis");
//            return redisProduct;
//        }

        //return optional product// graceful way
        Optional<Product> p=productRepository.findById(id);
        if(p.isPresent()){
            //redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_"+id, p);

            return p.get();
        }
        throw new ProductNotFoundException("Product not found in DB");

    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> product = new ArrayList<>();
        List<Product> p=productRepository.findAll();

//            for(Product p1 : p){
//                System.out.println(p1);
//            }

        return p;
    }

    @Override
    public Page<Product> getAllProductsbyPageNumber(int pageNumber, int pageSize, String fieldName) {
        Page<Product> products = productRepository.findAll(PageRequest.of(pageNumber, pageSize,
                Sort.by(fieldName).ascending()));

        return products;
    }

    @Override
    public Product deleteSingleProduct(Long id) throws ProductNotFoundException {
        //Optional<Product> p=productRepository.findById(id);
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public Product createProduct(Long id, String title, String description, Double price, String category, String image) {
        Product p=new Product();
        //Optional<Category> cat=categoryRepository.findByTitle(category);
        Category cat=categoryRepository.findByTitle(category);
        if(cat==null){
            //if category is not in our DB
            Category newCategory=new Category();
            newCategory.setTitle(category);
            //save the category row
            Category newCategory2=categoryRepository.save(newCategory);
            //set new row into product
            p.setCategory(newCategory2);

        }
        else{
            p.setCategory(cat);
        }
        p.setId(id);
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImageUrl(image);
        Product savedProducts=productRepository.save(p);
        return  savedProducts;

    }

    @Override
    public Product updateProduct(Long id, String title, Double price, String description, String image, String category) throws ProductNotFoundException {
        return null;
    }
}
