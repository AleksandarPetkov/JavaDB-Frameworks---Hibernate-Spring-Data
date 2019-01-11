package mostwantedtest.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedBankAccountDto {

    @XmlElement(name = "bank-account")
    private BankAccountXml[] bankAccounts;

    public SeedBankAccountDto() {
    }

    public BankAccountXml[] getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccountXml[] bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
