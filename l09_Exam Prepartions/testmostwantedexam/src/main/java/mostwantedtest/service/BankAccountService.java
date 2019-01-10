package mostwantedtest.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface BankAccountService {
    String readXMLFile() throws IOException;
    String seedBankAccounts() throws IOException, JAXBException;
}
