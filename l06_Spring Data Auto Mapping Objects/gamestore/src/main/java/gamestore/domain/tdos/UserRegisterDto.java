package gamestore.domain.tdos;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Incorrect email.")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Incorrect email / password")
    private String password;

    @NotNull
    private String confirmPassword;


    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
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

    public String getConfirmPaswword() {
        return confirmPassword;
    }

    public void setConfirmPaswword(String confirmPaswword) {
        this.confirmPassword = confirmPaswword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
