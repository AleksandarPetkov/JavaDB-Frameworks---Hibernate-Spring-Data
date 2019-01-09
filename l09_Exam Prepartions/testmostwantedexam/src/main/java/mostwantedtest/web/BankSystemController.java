package mostwantedtest.web;

import mostwantedtest.service.BranchService;
import mostwantedtest.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BankSystemController implements CommandLineRunner {

    private final BranchService branchService;
    private final EmployeeService employeeService;

    public BankSystemController(BranchService branchService, EmployeeService employeeService) {
        this.branchService = branchService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(this.branchService.seedBranches()); Ok
        System.out.println(this.employeeService.seedEmployee());
    }
}
