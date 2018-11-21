package gamestore.domain.tdos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Incorrect email.")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Incorrect email / password")
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
