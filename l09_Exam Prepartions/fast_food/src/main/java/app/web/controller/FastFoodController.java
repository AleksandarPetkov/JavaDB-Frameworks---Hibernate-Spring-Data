package app.web.controller;

import app.service.EmployeeService;
import app.service.ItemService;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class FastFoodController implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final ItemService itemService;
    private final OrderService orderService;

    @Autowired
    public FastFoodController(EmployeeService employeeService, ItemService itemService, OrderService orderService) {
        this.employeeService = employeeService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    // RUN
    @Override
    public void run(String... args) throws Exception {
//        this.seedAndPrintEmployeeDtos(); OK
//        this.seedAndPrintItemDtos(); OK
        this.orderService.seedOrders();
    }

    // SEED EMPLOYEES
    private void seedAndPrintEmployeeDtos() throws IOException {
        System.out.println(this.employeeService.seedEmployee());
    }

    // SEED ITEMS
    private void seedAndPrintItemDtos() throws IOException {
        System.out.println(this.itemService.seedItems());
    }

    //SEED ORDERS
    private void seedAndPrintIOrders() throws IOException, JAXBException {
        System.out.println(this.orderService.seedOrders());
    }
}
