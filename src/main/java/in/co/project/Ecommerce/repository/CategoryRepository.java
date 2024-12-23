package in.co.project.Ecommerce.repository;

import in.co.project.Ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);
}
