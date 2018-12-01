package car_dealer.repository;

import car_dealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT s FROM car_dealer.domain.entities.Supplier AS s WHERE s.isImporter = 0")
    List<Supplier> getLocalSuppliers();
}
