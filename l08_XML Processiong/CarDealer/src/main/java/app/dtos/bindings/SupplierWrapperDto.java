package app.dtos.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierWrapperDto implements Serializable {
    @XmlElement(name = "supplier")
    private List<SupplierDto> suppliers;

    public SupplierWrapperDto() {
        this.suppliers = new ArrayList<>();
    }

    public List<SupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}
