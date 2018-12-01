package car_dealer.domain.dtos.seedDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PartsSeedDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer quantity;

    public PartsSeedDto() {
    }

    @NotNull(message = "Invalid input.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Invalid input.")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Invalid input.")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
