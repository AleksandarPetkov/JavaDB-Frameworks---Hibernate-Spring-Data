package alararestaurant.domain.dtos.orderdto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderSeedDto {

    @XmlElement
    @NotNull
    private String customer;

    @XmlElement
    @NotNull
    @Size(min = 3, max = 30)
    private String employee;

    @XmlElement(name = "date-time")
    @NotNull
    private String dateTime;

    @XmlElement(name = "type")
    @NotNull
    private String orderType;

    @XmlElement(name = "items")
    private ItemRootSeedDto itemRootSeedDtol;

    public OrderSeedDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public ItemRootSeedDto getItemRootSeedDtol() {
        return itemRootSeedDtol;
    }

    public void setItemRootSeedDtol(ItemRootSeedDto itemRootSeedDtol) {
        this.itemRootSeedDtol = itemRootSeedDtol;
    }
}
