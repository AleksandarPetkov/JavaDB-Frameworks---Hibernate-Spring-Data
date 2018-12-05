package app.dtos.views.wrappers;

import app.dtos.views.SupplierView;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierWrapperView implements Serializable {
    @XmlElement(name = "supplier")
    private List<SupplierView> suppliers;

    public SupplierWrapperView() {
        this.suppliers = new ArrayList<>();
    }

    public List<SupplierView> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierView> suppliers) {
        this.suppliers = suppliers;
    }
}
