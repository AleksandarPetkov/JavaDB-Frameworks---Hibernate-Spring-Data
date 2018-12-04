package app.services.impl;

import app.dto.binding.UserDto;
import app.dto.view.*;
import app.entities.Product;
import app.entities.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(UserDto userDto) {
        User user = this.mapper.map(userDto, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void saveAll(List<UserDto> users) {
        for (UserDto user : users) {
            this.save(user);
        }
    }

    @Override
    public List<UserView> findAllUsersWithSoldProducts() {
        List<User> users = this.userRepository.findAllUsersWithSoldProducts();
        List<UserView> userViews = new ArrayList<>();
        for (User user : users) {
            UserView userView = new UserView();
            userView.setFirstName(user.getFirstName());
            userView.setLastName(user.getLastName());
            for (Product product : user.getSoldProducts()) {
                if (product.getBuyer() == null) {
                    continue;
                }
                SoldProductView soldProduct = new SoldProductView();
                soldProduct.setName(product.getName());
                soldProduct.setPrice(product.getPrice());
                soldProduct.setBuyerFirstName(product.getBuyer().getFirstName());
                soldProduct.setBuyerLastName(product.getBuyer().getLastName());
                userView.getSoldProducts().add(soldProduct);
            }
            userViews.add(userView);
        }
        return userViews;
    }

    @Override
    public UsersProductsWrapperView getUsersWithSoldProducts() {
        List<User> users = this.userRepository.findAllUsersWithSoldProducts();
        UsersProductsWrapperView output = new UsersProductsWrapperView();
        output.setUsersCount(users.size());

        for (User user : users) {
            UserNameAgeView userView = new UserNameAgeView();
            userView.setFirstName(user.getFirstName());
            userView.setLastName(user.getLastName());
            if (user.getAge() > 0) {
                userView.setAge(user.getAge());
            }
            SoldProductsView soldProductsView = new SoldProductsView();
            for (Product product : user.getSoldProducts()) {
                if (product.getBuyer() == null) {
                    continue;
                }
                ProductNamePriceView productView = new ProductNamePriceView();
                productView.setName(product.getName());
                productView.setPrice(product.getPrice());
                soldProductsView.getProducts().add(productView);
            }
            soldProductsView.setCount(soldProductsView.getProducts().size());
            userView.setSoldProducts(soldProductsView);
            output.getUsers().add(userView);
        }
        return output;
    }
}
