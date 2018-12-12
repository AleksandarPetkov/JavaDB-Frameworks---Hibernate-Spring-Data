package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//•	id – integer, Primary Key
//        •	name – text with min length 3 and max length 30 (required) - OK
//        •	age – integer in the range [15, 80] (required) - OK
//        •	position – the employee’s position (required) - OK

public class EmployeeSeedDto {

    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String name;

    @Expose
    @Min(15)
    @Max(80)
    @NotNull
    private int age;

    @Expose
    @Length(min = 3, max = 30)
    @NotNull
    private String position;

    public EmployeeSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
