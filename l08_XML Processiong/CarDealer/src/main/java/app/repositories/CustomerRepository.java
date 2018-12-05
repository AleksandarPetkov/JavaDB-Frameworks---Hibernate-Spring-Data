package app.repositories;

import app.dtos.views.CustomerPurchasesView;
import app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> getAllByBirthdate();

    @Query("SELECT new app.dtos.views.CustomerPurchasesView(c.name, c.purchases.size, " +
            "SUM(p.car.price)) " +
            "FROM Customer AS c JOIN c.purchases AS p " +
            "GROUP BY c.name " +
            "ORDER BY SUM(p.car.price) DESC, c.purchases.size DESC")
    List<CustomerPurchasesView> getAllCustomersWithPurchases();
}
