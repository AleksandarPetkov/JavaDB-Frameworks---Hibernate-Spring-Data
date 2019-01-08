package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeSeedDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = "C:\\Users\\HP\\Desktop\\Alara Restaurant_Skeleton\\AlaraRestaurant\\src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fIleUtil, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fIleUtil = fIleUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fIleUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder sb = new StringBuilder();

        EmployeeSeedDto[] employeeSeedDtos = this.gson.fromJson(employees, EmployeeSeedDto[].class);
        for (EmployeeSeedDto employeeSeedDto : employeeSeedDtos) {
            if (!this.validationUtil.isValid(employeeSeedDto)) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Position positionEntity = this.positionRepository.findByName(employeeSeedDto.getPosition()).orElse(null);
            if (positionEntity == null) {
                positionEntity = new Position();
                positionEntity.setName(employeeSeedDto.getPosition());
                this.positionRepository.saveAndFlush(positionEntity);
            }

            Employee employeeEntity = this.modelMapper.map(employeeSeedDto, Employee.class);
            employeeEntity.setPosition(positionEntity);
            this.employeeRepository.saveAndFlush(employeeEntity);
            sb.append(String.format("Record %s successfully imported.", employeeEntity.getName())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
