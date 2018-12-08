package mostwanted.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

//    •	name – a string (required, unique).
//            •	age – an integer number.
//            •	bounty – a decimal data type.
//            •	homeTown – a Town entity.
//            •	cars – a collection of Car entity.
@Entity(name = "racers")
public class Racer extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal bounty;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer", targetEntity = Car.class)
    private List<Car> cars;

    public Racer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
