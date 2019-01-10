package mostwantedtest.web;

import mostwantedtest.service.BranchService;
import mostwantedtest.service.ClientService;
import mostwantedtest.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BankSystemController implements CommandLineRunner {

    private final BranchService branchService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    public BankSystemController(BranchService branchService, EmployeeService employeeService, ClientService clientService) {
        this.branchService = branchService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.branchService.seedBranches());
        System.out.println(this.employeeService.seedEmployee());
        System.out.println(this.clientService.seedClients());
    }
}
