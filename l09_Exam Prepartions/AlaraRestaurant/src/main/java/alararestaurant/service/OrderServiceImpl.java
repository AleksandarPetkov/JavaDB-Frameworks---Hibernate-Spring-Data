package alararestaurant.service;

import alararestaurant.domain.dtos.orderdto.ItemSeedDto;
import alararestaurant.domain.dtos.orderdto.OrderRootSeedDto;
import alararestaurant.domain.dtos.orderdto.OrderSeedDto;
import alararestaurant.domain.entities.*;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDERS_FILE_PATH = "C:\\Users\\HP\\Desktop\\Alara Restaurant_Skeleton\\AlaraRestaurant\\src\\main\\resources\\files\\orders.xml";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final FileUtil fIleUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public OrderServiceImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, FileUtil fIleUtil, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.fIleUtil = fIleUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fIleUtil.readFile(ORDERS_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        OrderRootSeedDto orderRootSeedDto = this.xmlParser.parseXml(OrderRootSeedDto.class, ORDERS_FILE_PATH);
        for (OrderSeedDto orderSeedDto : orderRootSeedDto.getOrderSeedDtos()) {
            if (!this.validationUtil.isValid(orderSeedDto)){
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            //Check for exist employee
            Employee employee = employeeRepository.findByName(orderSeedDto.getEmployee()).orElse(null);
            if (employee == null) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            //Check order type
            OrderType type = null;
            if (orderSeedDto.getOrderType().equals("ForHere")){
                type = OrderType.valueOf("ForHere");
            } else if (orderSeedDto.getOrderType().equals("ToGo")){
                type = OrderType.valueOf("ToGo");
            } else{
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            //Create Order and Set data
            Order order = this.modelMapper.map(orderSeedDto, Order.class);
            order.setDateTime(LocalDateTime.parse(orderSeedDto.getDateTime(), DATE_TIME_FORMAT));
            order.setType(type);
            order.setEmployee(employee);

            List<OrderItem> orderItemList = new ArrayList<>();
            for (ItemSeedDto itemSeedDto : orderSeedDto.getItemRootSeedDtol().getItemSeedDtos()) {
                OrderItem orderItem = new OrderItem();

                Item item = this.itemRepository.findByName(itemSeedDto.getName()).orElse(null);
                if (item == null){
                    sb.append("Invalid data format.").append(System.lineSeparator());
                    continue;
                }

                orderItem.setItem(item);
                orderItem.setOrder(order);
                orderItemList.add(orderItem);
            }

            this.orderRepository.saveAndFlush(order);
            this.orderItemRepository.saveAll(orderItemList);
            sb.append(String.format("Order for %s on %s added", order.getCustomer(), order.getDateTime())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        // TODO : Implement me
        return null;
    }
}
