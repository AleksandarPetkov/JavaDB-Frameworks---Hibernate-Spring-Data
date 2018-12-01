package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.OrderedCustomersDto;
import car_dealer.domain.dtos.seedDtos.CustomersSeedDto;
import car_dealer.domain.entities.Customer;
import car_dealer.repository.CustomerRepositoty;
import car_dealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepositoty customerRepositoty;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepositoty customerRepositoty, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.customerRepositoty = customerRepositoty;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCustomers(CustomersSeedDto[] dtos) {
        StringBuilder sb = new StringBuilder();

        for (CustomersSeedDto dto : dtos) {
            if (!this.validatorUtil.isValid(dto)){

                sb.append("Not valid").append(System.lineSeparator());
                continue;
            }

            Customer customerEntity = this.modelMapper.map(dto, Customer.class);
            String[] dateFormat = dto.getBirthDate().split("T+");
            customerEntity.setBirthDate(LocalDate.parse(dateFormat[0]));

            this.customerRepositoty.saveAndFlush(customerEntity);
        }

    }

    @Override
    public List<OrderedCustomersDto> orderedCustomers() {
        List<Customer> customers = this.customerRepositoty.getAllByBirthdate();
        List<OrderedCustomersDto> orderedCustomersDtos = new ArrayList<>();

        for (Customer customer : customers) {
            OrderedCustomersDto dto = this.modelMapper.map(customer, OrderedCustomersDto.class);
            orderedCustomersDtos.add(dto);
        }
        return orderedCustomersDtos;
    }


}
