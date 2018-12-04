package app.dto.view;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNameAgeView implements Comparable<UserNameAgeView> {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "sold-products")
    private SoldProductsView soldProducts;

    public UserNameAgeView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductsView getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductsView soldProducts) {
        this.soldProducts = soldProducts;
    }

    @Override
    public int compareTo(UserNameAgeView o) {
        int comp = Integer.compare(o.getSoldProducts().getCount(), this.getSoldProducts().getCount());
        if (comp == 0) {
            comp = this.getLastName().compareTo(o.getLastName());
        }
        return comp;
    }
}
