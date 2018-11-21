package gamestore.web.controller;

import gamestore.domain.tdos.UserLoginDto;
import gamestore.domain.tdos.UserLogoutDto;
import gamestore.domain.tdos.UserRegisterDto;
import gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameController implements CommandLineRunner {

    private String logginUser;

    private final UserService userService;

    @Autowired
    public GameController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\|");

            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);
                    System.out.println(this.userService.userRegister(userRegisterDto));
                    break;
                case "LoginUser":

                    if (this.logginUser == null) {
                        UserLoginDto userLoginDto = new UserLoginDto(tokens[1], tokens[2]);
                        String logInfo = this.userService.userLogin(userLoginDto);

                        if (logInfo.contains("Successfully logged!")) {

                            this.logginUser = userLoginDto.getEmail();
                        }
                        System.out.println(logInfo);

                    } else {
                        System.out.println("User already logged!");
                    }
                    break;
                case "Logout":

                    if (this.logginUser == null){
                        System.out.println("Cannot log out. No user was logged in!");
                    } else {
                        UserLogoutDto user = new UserLogoutDto(this.logginUser);
                        String logoutInfo = this.userService.userLogout(user);
                        System.out.println(logoutInfo);
                        this.logginUser = null;
                    }
                    break;
                case "AddGame":

                        break;
            }
        }
    }
}
