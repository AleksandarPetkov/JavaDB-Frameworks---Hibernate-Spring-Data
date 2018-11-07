package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wizard_deposits")
public class Wizard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50)
    private String firtName;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "age")
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private int magicWandSize;

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private LocalDateTime depositStartDate;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;


    @Column(name = "deposit_interest")
    private BigDecimal depositInterest;

    @Column(name = "deposit_charge")
    private BigDecimal depositCharge;

    @Column(name = "deposit_expiration_date")
    private LocalDateTime depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public Wizard() {
    }

    public Wizard(String firtName, String lastName, String notes, int age, String magicWandCreator, int magicWandSize, String depositGroup, LocalDateTime depositStartDate, BigDecimal depositAmount, BigDecimal depositInterest, BigDecimal depositCharge, LocalDateTime depositExpirationDate, boolean isDepositExpired) {
        this.firtName = firtName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = depositStartDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpirationDate = depositExpirationDate;
        this.isDepositExpired = isDepositExpired;
    }
}
