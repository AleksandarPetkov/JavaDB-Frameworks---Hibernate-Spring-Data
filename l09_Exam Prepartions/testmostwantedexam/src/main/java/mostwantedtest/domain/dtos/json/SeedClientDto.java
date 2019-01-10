package mostwantedtest.domain.dtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.NotNull;

public class SeedClientDto {

    @Expose
    @NotNull
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @NotNull
    @SerializedName("last_name")
    private String lastName;

    @Expose
    private int age;

    @Expose
    @NotNull
    @SerializedName("appointed_employee")
    private String employee;

    public SeedClientDto() {
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
