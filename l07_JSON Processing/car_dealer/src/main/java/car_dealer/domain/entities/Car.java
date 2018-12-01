package car_dealer.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "cars")
public class Car extends BaseEntity {

    private String make;

    private String model;

    @Column(name = "distance_travelled")
    private Long travelledDistance;


    @ManyToMany
    @JoinTable(name = "cars_parts",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
    private Set<Part> parts;

    @OneToMany(targetEntity = Sale.class, mappedBy = "car")
    private Set<Sale> sales;

    public Car() {
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }


    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
