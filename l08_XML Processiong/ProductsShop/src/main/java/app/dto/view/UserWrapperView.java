package app.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapperView {
    @XmlElement(name = "user")
    private List<UserView> users;

    public UserWrapperView() {
    }

    public List<UserView> getUsers() {
        return users;
    }

    public void setUsers(List<UserView> users) {
        this.users = users;
    }
}
