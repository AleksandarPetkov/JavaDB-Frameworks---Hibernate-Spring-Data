package app.services.api;

import app.dto.binding.UserDto;
import app.dto.view.UserView;
import app.dto.view.UsersProductsWrapperView;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    void saveAll(List<UserDto> users);

    List<UserView> findAllUsersWithSoldProducts();

    UsersProductsWrapperView getUsersWithSoldProducts();
}
