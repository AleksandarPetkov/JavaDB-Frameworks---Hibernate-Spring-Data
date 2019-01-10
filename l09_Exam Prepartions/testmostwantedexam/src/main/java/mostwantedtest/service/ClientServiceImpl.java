package mostwantedtest.service;

import com.google.gson.Gson;
import mostwantedtest.domain.dtos.json.SeedClientDto;
import mostwantedtest.domain.entities.Client;
import mostwantedtest.domain.entities.Employee;
import mostwantedtest.repository.ClientRepository;
import mostwantedtest.repository.EmployeeRepository;
import mostwantedtest.util.FileUtil;
import mostwantedtest.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENTS_FILE_PATH = "D:\\SoftUni\\JavaDB-Frameworks---Hibernate-Spring-Data\\l09_Exam Prepartions\\testmostwantedexam\\src\\main\\resources\\files\\json\\clients.json";

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fIleUtil, ValidationUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fIleUtil = fIleUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String readJsonFile() throws IOException {
        return this.fIleUtil.readFile(CLIENTS_FILE_PATH);
    }

    @Override
    public String seedClients() throws IOException {
        StringBuilder sb = new StringBuilder();
        String fileContent = this.readJsonFile();

        SeedClientDto[] seedClientDtos = this.gson.fromJson(fileContent, SeedClientDto[].class);
        for (SeedClientDto seedClientDto : seedClientDtos) {
            String[] employeeName = seedClientDto.getEmployee().split("\\s+");
            Employee employee = this.employeeRepository.findByFirstNameAndLastName(employeeName[0], employeeName[1]).orElse(null);

            if (!this.validatorUtil.isValid(seedClientDto) || employee == null){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Client client = this.modelMapper.map(seedClientDto, Client.class);
            client.setFullName(seedClientDto.getFirstName() + " " + seedClientDto.getLastName());
            client.getEmployees().add(employee);
            sb.append(String.format("Successfully imported Client - %s ",client.getFullName()))
                    .append(System.lineSeparator());
            this.clientRepository.saveAndFlush(client);
        }

        return sb.toString();
    }
}
