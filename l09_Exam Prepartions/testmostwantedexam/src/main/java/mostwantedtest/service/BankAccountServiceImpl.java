package mostwantedtest.service;

import mostwantedtest.domain.dtos.xml.BankAccountXml;
import mostwantedtest.domain.dtos.xml.SeedBankAccountDto;
import mostwantedtest.domain.entities.BankAccount;
import mostwantedtest.domain.entities.Client;
import mostwantedtest.repository.BankAccountRepository;
import mostwantedtest.repository.ClientRepository;
import mostwantedtest.util.FileUtil;
import mostwantedtest.util.ValidationUtil;
import mostwantedtest.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    private static final String BANK_ACCOUNT_FILE_PATH = "D:\\SoftUni\\JavaDB-Frameworks---Hibernate-Spring-Data\\l09_Exam Prepartions\\testmostwantedexam\\src\\main\\resources\\files\\xml\\bank-accounts.xml";

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String readXMLFile() throws IOException {
        return this.fileUtil.readFile(BANK_ACCOUNT_FILE_PATH);
    }

    @Override
    public String seedBankAccounts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(SeedBankAccountDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        SeedBankAccountDto orderRootSeedDto = (SeedBankAccountDto) unmarshaller
                .unmarshal(new File(BANK_ACCOUNT_FILE_PATH));

        for (BankAccountXml bankAccount : orderRootSeedDto.getBankAccounts()) {
            Client client = this.clientRepository.findByFullName(bankAccount.getClient()).orElse(null);

            if (!this.validationUtil.isValid(bankAccount) || client == null){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            BankAccount currentBankAccount = this.modelMapper.map(bankAccount, BankAccount.class);
           currentBankAccount.setClient(client);

           this.bankAccountRepository.saveAndFlush(currentBankAccount);
            sb.append(String.format("Successfully imported Bank Account with number: %s.",bankAccount.getAccountNumber()))
                    .append(System.lineSeparator());
        }
        System.out.println();

        return sb.toString();
    }
}
