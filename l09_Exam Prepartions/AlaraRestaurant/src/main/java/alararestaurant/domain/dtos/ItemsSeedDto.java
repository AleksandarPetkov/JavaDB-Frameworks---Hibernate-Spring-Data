package alararestaurant.domain.dtos;


import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

//      item name – text with min length 3 and max length 30 (required, unique)
//      category name – text with min length 3 and max length 30 (required)
//      price – decimal (non-negative, minimum value: 0.01, required)
public class ItemsSeedDto {

    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String name;

    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String category;

    @Expose
    @DecimalMin("0.01")
    @NotNull
    private BigDecimal price;

    public ItemsSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
