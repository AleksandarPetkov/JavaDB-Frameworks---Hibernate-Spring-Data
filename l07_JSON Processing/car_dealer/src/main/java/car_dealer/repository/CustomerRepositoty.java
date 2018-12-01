package car_dealer.repository;

import car_dealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepositoty extends JpaRepository<Customer, Integer> {


    @Query("SELECT c FROM car_dealer.domain.entities.Customer AS c ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> getAllByBirthdate();
}
