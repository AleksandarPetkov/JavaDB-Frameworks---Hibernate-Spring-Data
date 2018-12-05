package app.dtos.views.wrappers;

import app.dtos.views.CustomerPurchasesView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchasesWrapperView implements Serializable {
    @XmlElement(name = "customer")
    private List<CustomerPurchasesView> customers;

    public CustomerPurchasesWrapperView() {
        this.customers = new ArrayList<>();
    }

    public List<CustomerPurchasesView> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerPurchasesView> customers) {
        this.customers = customers;
    }
}
