package myprojectforusersystem.users.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @OneToMany(mappedBy = "bornTown")
    private Set<User> bornUsers;

    @OneToMany(mappedBy = "currentlyLivingTown")
    private Set<User> currentlyLivingUsers;

    public Town() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Set<User> getBornUsers() {
        return bornUsers;
    }

    public Set<User> getCurrentlyLivingUsers() {
        return currentlyLivingUsers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBornUsers(Set<User> bornUsers) {
        this.bornUsers = bornUsers;
    }

    public void setCurrentlyLivingUsers(Set<User> currentlyLivingUsers) {
        this.currentlyLivingUsers = currentlyLivingUsers;
    }
}
