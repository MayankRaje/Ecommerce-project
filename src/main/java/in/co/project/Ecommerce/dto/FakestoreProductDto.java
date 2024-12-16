package in.co.project.Ecommerce.dto;

import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;
import in.co.project.Ecommerce.service.ProductService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakestoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    //get the product of our Implementation //values from fakestore

    public Product getProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
//        product.setCategory(category);
        product.setImageUrl(image);

        Category cat = new Category();
        cat.setTitle(category);
        product.setCategory(cat);

        return product;
    }
}
