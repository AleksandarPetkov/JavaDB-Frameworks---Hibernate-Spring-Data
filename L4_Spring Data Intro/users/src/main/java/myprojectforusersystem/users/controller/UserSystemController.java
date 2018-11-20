package myprojectforusersystem.users.controller;
import myprojectforusersystem.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class UserSystemController implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public UserSystemController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.getUsersByDomain();
    }

    private void getUsersByDomain(){
        Scanner scanner = new Scanner(System.in);
        String domain = scanner.nextLine();

        this.userService.findUsersByDomain(domain).forEach(System.out::println);
    }
}
