package car_dealer.domain.dtos.queringDtos;

import car_dealer.domain.entities.Sale;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.Set;

public class OrderedCustomersDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private LocalDate birthDate;

    @Expose
    private boolean isYongDriver;

    @Expose
    private Set<Sale> sales;

    public OrderedCustomersDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYongDriver() {
        return isYongDriver;
    }

    public void setYongDriver(boolean yongDriver) {
        isYongDriver = yongDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
