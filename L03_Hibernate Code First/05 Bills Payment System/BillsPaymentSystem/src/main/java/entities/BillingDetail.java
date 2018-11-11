package entities;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BillingDetail {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public BillingDetail() {
    }

    public String getNumber() {
        return number;
    }

    public User getOwner() {
        return owner;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
