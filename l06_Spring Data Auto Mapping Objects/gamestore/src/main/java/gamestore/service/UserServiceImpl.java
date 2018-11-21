package gamestore.service;

import gamestore.domain.entities.Role;
import gamestore.domain.entities.User;
import gamestore.domain.tdos.UserLoginDto;
import gamestore.domain.tdos.UserLogoutDto;
import gamestore.domain.tdos.UserRegisterDto;
import gamestore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String userRegister(UserRegisterDto userRegisterDto) {
        Validator validator = Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPaswword())){
            sb.append("Password is not correct.").append(System.lineSeparator());

        } else if (validator.validate(userRegisterDto).size() > 0){
            // validator.validate: return Collection with Exceptions!
            for (ConstraintViolation<UserRegisterDto> violations : validator.validate(userRegisterDto)) {
                sb.append(violations.getMessage()).append(System.lineSeparator());
            }

        } else {
            //When all Dto validation checks are completed, i need to map it
            User entity =  this.modelMapper.map(userRegisterDto, User.class);

            if (this.userRepository.count() == 0){
                entity.setRole(Role.ADMIN);
            } else {
                entity.setRole(Role.USER);
            }

            sb.append(String.format("%s was registered", entity.getFullName()))
                    .append(System.lineSeparator());
            this.userRepository.saveAndFlush(entity);
        }

        return sb.toString().trim();
    }

    @Override
    public String userLogin(UserLoginDto userLoginDto) {
        Validator validator = Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        StringBuilder sb = new StringBuilder();
        if (validator.validate(userLoginDto).size() > 0){
            for (ConstraintViolation<UserLoginDto> violations : validator.validate(userLoginDto)) {
                sb.append(violations.getMessage()).append(System.lineSeparator());
            }
        }else {
            User entity = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);

            if (entity == null){
              return   sb.append("User does not exist!").append(System.lineSeparator()).toString();
            } else if (!entity.getPassword().equals(userLoginDto.getPassword())){
                return   sb.append("Password is not correct!").append(System.lineSeparator()).toString();
            }

            sb.append(String.format("Successfully logged in %s", entity.getFullName()))
            .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String userLogout(UserLogoutDto userLogoutDto) {
        User entity = this.userRepository.findByEmail(userLogoutDto.getEmail()).orElse(null);

        StringBuilder sb = new StringBuilder();
        if (entity == null) {
            return sb.append("User does not exist!").append(System.lineSeparator()).toString();
        }
        sb.append(String.format("User %s successfully logged out", entity.getFullName()));

        return sb.toString();
    }

}
