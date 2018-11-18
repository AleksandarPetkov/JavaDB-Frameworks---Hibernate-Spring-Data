package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", length = 15, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 15, nullable = false)
    private String lastName;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 25, nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<BillingDetail> billingDetailSet;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<BillingDetail> getBillingDetailSet() {
        return billingDetailSet;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBillingDetailSet(Set<BillingDetail> billingDetailSet) {
        this.billingDetailSet = billingDetailSet;
    }
}
