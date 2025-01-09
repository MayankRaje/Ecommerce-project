package in.co.project.Ecommerce.repository;

import in.co.project.Ecommerce.models.Category;
import in.co.project.Ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Optional<Category> findByTitle(String title);
    Category findByTitle(String title);
}
