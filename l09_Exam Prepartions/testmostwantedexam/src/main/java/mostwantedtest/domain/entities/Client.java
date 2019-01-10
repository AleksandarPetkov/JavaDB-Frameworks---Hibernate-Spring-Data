package mostwantedtest.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//•	full_name – a string (required).
//        •	age – an integer number.
//        •	bank_account – a Bank Account entity (One).
@Entity(name = "clients")
public class Client extends BaseEntity{

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column
    private int age;

    @OneToOne(targetEntity = BankAccount.class, mappedBy = "client")
    private BankAccount bankAccount;


    @ManyToMany
    @JoinTable(name = "clients_employees",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> employees;

    public Client() {
        this.employees = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
