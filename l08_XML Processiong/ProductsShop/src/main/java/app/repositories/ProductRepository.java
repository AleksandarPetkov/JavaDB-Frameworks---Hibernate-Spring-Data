package app.repositories;

import app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetweenAndBuyerIdIsNullOrderByPrice(BigDecimal from, BigDecimal to);
}
