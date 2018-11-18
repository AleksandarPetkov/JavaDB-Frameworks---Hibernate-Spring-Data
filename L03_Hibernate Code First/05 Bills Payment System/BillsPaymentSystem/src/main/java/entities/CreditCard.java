package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail {
    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    public CreditCard() {
    }

    public String getCardType() {
        return cardType;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
