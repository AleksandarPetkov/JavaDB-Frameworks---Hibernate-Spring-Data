package app.dto.view;


import javax.xml.bind.annotation.*;
import java.util.Set;
import java.util.TreeSet;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersProductsWrapperView {
    @XmlAttribute(name = "count")
    private int usersCount;

    @XmlElement(name = "user")
    private Set<UserNameAgeView> users;

    public UsersProductsWrapperView() {
        this.users = new TreeSet<>();
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserNameAgeView> getUsers() {
        return users;
    }

    public void setUsers(Set<UserNameAgeView> users) {
        this.users = users;
    }
}
