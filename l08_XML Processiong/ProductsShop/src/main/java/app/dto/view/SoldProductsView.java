package app.dto.view;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsView {
    @XmlAttribute
    private int count;

    @XmlElement(name = "product")
    private List<ProductNamePriceView> products;

    public SoldProductsView() {
        this.products = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductNamePriceView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePriceView> products) {
        this.products = products;
    }
}
