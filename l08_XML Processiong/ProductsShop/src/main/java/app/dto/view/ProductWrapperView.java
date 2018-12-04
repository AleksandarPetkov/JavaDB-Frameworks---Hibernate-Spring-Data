package app.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWrapperView {
    @XmlElement(name = "product")
    private List<ProductView> products;

    public ProductWrapperView() {
        this.products = new ArrayList<>();
    }

    public List<ProductView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductView> products) {
        this.products = products;
    }
}
