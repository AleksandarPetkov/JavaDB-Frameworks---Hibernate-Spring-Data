package app.domain.dtos.orderSeedXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderSeedDto {

    @XmlElement
    private String customer;

    @XmlElement
    private String employee;

    @XmlElement
    private String date;

    @XmlElement
    private String type;

    @XmlElement(name = "items")
    private ItemRoodSeedDto itemRoodSeedDto;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemRoodSeedDto getItemRoodSeedDto() {
        return itemRoodSeedDto;
    }

    public void setItemRoodSeedDto(ItemRoodSeedDto itemRoodSeedDto) {
        this.itemRoodSeedDto = itemRoodSeedDto;
    }
}
