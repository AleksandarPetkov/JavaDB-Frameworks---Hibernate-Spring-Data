package app.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryWrapperView {
    @XmlElement(name = "category")
    private List<CategoryView> categories;

    public CategoryWrapperView() {
    }

    public List<CategoryView> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryView> categories) {
        this.categories = categories;
    }
}
