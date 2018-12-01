package car_dealer.domain.dtos.queringDtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public PartDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
