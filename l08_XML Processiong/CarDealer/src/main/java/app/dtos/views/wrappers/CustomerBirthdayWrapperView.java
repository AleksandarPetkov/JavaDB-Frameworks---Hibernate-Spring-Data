package app.dtos.views.wrappers;

import app.dtos.views.CustomerByBirthdateView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerBirthdayWrapperView implements Serializable {
    @XmlElement(name = "customer")
    private List<CustomerByBirthdateView> customers;

    public CustomerBirthdayWrapperView() {
        this.customers = new ArrayList<>();
    }

    public List<CustomerByBirthdateView> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<CustomerByBirthdateView> customers) {
        this.customers = customers;
    }
}
