package mostwantedtest.service;

import com.google.gson.Gson;
import mostwantedtest.domain.dtos.json.SeedEmployeeDto;
import mostwantedtest.repository.BranchRepository;
import mostwantedtest.repository.EmployeeRepository;
import mostwantedtest.util.FileUtil;
import mostwantedtest.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

        return sb.toString();
    }
}
