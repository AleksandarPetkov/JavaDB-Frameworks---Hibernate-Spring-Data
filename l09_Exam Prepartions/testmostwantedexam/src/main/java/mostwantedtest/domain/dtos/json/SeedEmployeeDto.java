package mostwantedtest.domain.dtos.json;
//full_name" : "Milty Dyett",
//        "salary" : 213270.78,
//        "started_on" : "2017-06-10",
//        "branch_name" : "Mendota Branch",

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SeedEmployeeDto {

    @Expose
    @NotNull
    private String fullName;

    @Expose
    @Min(0)
    private BigDecimal salary;

    @Expose
    private LocalDate startedOn;

    @Expose
    @NotNull
    private String branchName;

    public SeedEmployeeDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
