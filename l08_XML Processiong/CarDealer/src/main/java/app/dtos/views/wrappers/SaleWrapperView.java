package app.dtos.views.wrappers;

import app.dtos.views.SaleView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWrapperView implements Serializable {
    @XmlElement(name = "sale")
    private List<SaleView> sales;

    public SaleWrapperView() {
        this.sales = new ArrayList<>();
    }

    public List<SaleView> getSales() {
        return sales;
    }

    public void setSales(List<SaleView> sales) {
        this.sales = sales;
    }
}
