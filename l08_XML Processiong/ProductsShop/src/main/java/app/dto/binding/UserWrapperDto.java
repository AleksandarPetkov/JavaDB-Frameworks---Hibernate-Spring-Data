package app.dto.binding;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapperDto implements Serializable {
    @XmlElement(name = "user")
    private List<UserDto> users;

    public UserWrapperDto() {
        this.users = new ArrayList<>();
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
