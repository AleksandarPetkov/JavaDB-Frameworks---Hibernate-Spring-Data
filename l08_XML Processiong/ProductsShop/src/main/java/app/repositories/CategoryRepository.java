package app.repositories;

import app.dto.view.CategoryView;
import app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new app.dto.view.CategoryView(c.name, c.products.size, AVG(p.price), SUM(p.price)) " +
            "FROM Category AS c " +
            "JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(c.products.size) DESC")
    List<CategoryView> getCategoriesByProductsCount();
}
