package app.service;

import app.domain.dtos.EmployeeSeedDto;
import app.domain.entities.Employee;
import app.domain.entities.Position;
import app.repository.EmployeeRepository;
import app.repository.PositionRepository;
import app.util.FIleUtil;
import app.util.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_FILE_PATH = "D:\\fast_food\\src\\main\\resources\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FIleUtil fIleUtil;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FIleUtil fIleUtil, ValidatorUtil validatorUtil, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fIleUtil = fIleUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String readEmployees() throws IOException {
        return this.fIleUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String seedEmployee() throws IOException {
        StringBuilder sb = new StringBuilder();

        EmployeeSeedDto[] employeeSeedDts = this.gson.fromJson(this.readEmployees(), EmployeeSeedDto[].class);
        for (EmployeeSeedDto employeeSeedDto : employeeSeedDts) {
            if (!this.validatorUtil.isValid(employeeSeedDto)){
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Position positionEntity = this.positionRepository.findByPosition(employeeSeedDto.getPosition()).orElse(null);
            if (positionEntity == null){
                positionEntity = new Position();
                positionEntity.setPosition(employeeSeedDto.getPosition());
                this.positionRepository.saveAndFlush(positionEntity);
            }


            Employee employeeEntity = this.modelMapper.map(employeeSeedDto, Employee.class);
            employeeEntity.setPosition(positionEntity);
            this.employeeRepository.saveAndFlush(employeeEntity);
            sb.append(String.format("Record %s successfully imported!", employeeEntity.getName())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
