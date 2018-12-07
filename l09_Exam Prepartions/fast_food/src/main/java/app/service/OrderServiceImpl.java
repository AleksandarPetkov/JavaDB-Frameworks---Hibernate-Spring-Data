package app.service;

import app.domain.dtos.ItemSeedDto;
import app.domain.dtos.orderSeedXmlDto.OrderRootSeedDto;
import app.domain.dtos.orderSeedXmlDto.OrderSeedDto;
import app.domain.entities.Employee;
import app.domain.entities.Order;
import app.repository.EmployeeRepository;
import app.repository.ItemRepository;
import app.repository.OrderRepository;
import app.util.FIleUtil;
import app.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_FILE_PATH = "D:\\fast_food\\src\\main\\resources\\xml\\orders.xml";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final EmployeeRepository employeeRepository;
    private final FIleUtil fIleUtil;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, EmployeeRepository employeeRepository, FIleUtil fIleUtil, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.employeeRepository = employeeRepository;
        this.fIleUtil = fIleUtil;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public String seedOrders() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(OrderRootSeedDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        OrderRootSeedDto orderRootSeedDto = (OrderRootSeedDto) unmarshaller
                .unmarshal(new File(ORDER_FILE_PATH));

        for (OrderSeedDto orderDto : orderRootSeedDto.getOrderSeedDtos()) {

            Employee employeeEntity = this.employeeRepository.findByName(orderDto.getEmployee()).orElse(null);
            if (!this.validatorUtil.isValid(orderDto) || employeeEntity == null) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Order orderEntity = this.modelMapper.map(orderDto, Order.class);
            orderEntity.setEmployee(employeeEntity);


        }
        return null;
    }
}
