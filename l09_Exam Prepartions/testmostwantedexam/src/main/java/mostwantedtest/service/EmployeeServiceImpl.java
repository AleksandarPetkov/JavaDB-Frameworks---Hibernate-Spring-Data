package mostwantedtest.service;

import com.google.gson.Gson;
import mostwantedtest.domain.dtos.json.SeedEmployeeDto;
import mostwantedtest.domain.entities.Branch;
import mostwantedtest.domain.entities.Employee;
import mostwantedtest.repository.BranchRepository;
import mostwantedtest.repository.EmployeeRepository;
import mostwantedtest.util.FileUtil;
import mostwantedtest.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEE_FILE_JSON_PATH = "D:\\SoftUni\\JavaDB-Frameworks---Hibernate-Spring-Data\\l09_Exam Prepartions\\testmostwantedexam\\src\\main\\resources\\files\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ValidationUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final FileUtil fIleUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, ValidationUtil validatorUtil, ModelMapper modelMapper, Gson gson, FileUtil fIleUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.fIleUtil = fIleUtil;
    }

    @Override
    public String readJsonFile() throws IOException {
        return this.fIleUtil.readFile(EMPLOYEE_FILE_JSON_PATH);
    }

    @Override
    public String seedEmployee() throws IOException {
        StringBuilder sb = new StringBuilder();
        String fileContent = this.readJsonFile();

        SeedEmployeeDto[] employeeDtos = this.gson.fromJson(fileContent, SeedEmployeeDto[].class);


        for (SeedEmployeeDto employeeDto : employeeDtos) {
            Branch branch = this.branchRepository.findByName(employeeDto.getBranchName());
            if (branch == null){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            if (!this.validatorUtil.isValid(employeeDto)){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            String[] fullName = employeeDto.getFullName().split("\\s+");
            employee.setFirstName(fullName[0]);
            employee.setLastName(fullName[1]);
            employee.setBranch(branch);
            LocalDate startedOn = LocalDate.parse(employeeDto.getStartedOn());
            employee.setStartedOn(startedOn);

            sb.append(String.format("Successfully imported Employee - %s %s.",employee.getFirstName(), employee.getLastName()))
                    .append(System.lineSeparator());
            this.employeeRepository.saveAndFlush(employee);
        }
        return sb.toString();
    }
}
