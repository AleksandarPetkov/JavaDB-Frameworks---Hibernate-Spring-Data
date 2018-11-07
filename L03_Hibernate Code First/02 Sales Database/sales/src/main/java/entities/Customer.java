package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "credit_card_number", unique = true)
    private String creditCardNumber;

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    private Set<Sale> sales;

    public Customer() {
    }
}
