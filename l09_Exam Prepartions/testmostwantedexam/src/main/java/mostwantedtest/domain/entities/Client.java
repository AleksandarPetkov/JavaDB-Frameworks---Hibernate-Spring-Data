package mostwantedtest.domain.entities;

import javax.persistence.*;

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

    public Client() {
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
}
