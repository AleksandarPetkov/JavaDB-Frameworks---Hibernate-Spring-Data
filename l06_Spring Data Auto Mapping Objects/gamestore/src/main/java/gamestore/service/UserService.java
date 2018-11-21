package gamestore.service;

import gamestore.domain.tdos.UserLoginDto;
import gamestore.domain.tdos.UserLogoutDto;
import gamestore.domain.tdos.UserRegisterDto;

public interface UserService {
    String userRegister(UserRegisterDto userRegisterDto);

    String userLogin(UserLoginDto userLoginDto);

    String userLogout(UserLogoutDto userLogoutDto);
}
