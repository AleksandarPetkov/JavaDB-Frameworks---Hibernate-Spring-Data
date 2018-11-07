package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sale")
public class Sale extends BaseEntity{

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;

    @Column(name = "date")
    private LocalDate date;

    public Sale() {
    }
}
