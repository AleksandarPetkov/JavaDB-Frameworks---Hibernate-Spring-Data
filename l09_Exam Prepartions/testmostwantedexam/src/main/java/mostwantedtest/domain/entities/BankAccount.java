package mostwantedtest.domain.entities;
//•	account_number – a string (required).
//        •	balance – a decimal data type.
//        •	client – a Client entity (One).
//        •	cards – a collection of Card entity

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "bank_accounts")
public class BankAccount extends BaseEntity{

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column
    private BigDecimal balance;

    @OneToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "bankAccount", targetEntity = Card.class)
    private List<Card> cards;

    public BankAccount() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
