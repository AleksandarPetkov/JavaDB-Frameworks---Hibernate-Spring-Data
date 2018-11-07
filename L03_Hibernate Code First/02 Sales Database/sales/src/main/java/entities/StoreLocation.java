package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation extends BaseEntity {

    @Column(name = "location_name")
    private String locationName;

    @OneToMany(targetEntity = Sale.class, mappedBy = "storeLocation")
    private Set<Sale> sales;
}
