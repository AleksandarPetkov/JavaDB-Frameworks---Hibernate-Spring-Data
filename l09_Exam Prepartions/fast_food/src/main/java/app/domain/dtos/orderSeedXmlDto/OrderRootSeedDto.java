package app.domain.dtos.orderSeedXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderRootSeedDto {

    @XmlElement(name = "order")
    private OrderSeedDto[] orderSeedDtos;

    public OrderRootSeedDto() {
    }

    public OrderSeedDto[] getOrderSeedDtos() {
        return orderSeedDtos;
    }

    public void setOrderSeedDtos(OrderSeedDto[] orderSeedDtos) {
        this.orderSeedDtos = orderSeedDtos;
    }
}
