package myprojectforusersystem.users.service;
import myprojectforusersystem.users.domain.entities.User;
import myprojectforusersystem.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> findUsersByDomain(String domain) {
       List<User> users = this.userRepository.findAllByEmailEndsWith(domain);
       if (users.isEmpty()){
           System.out.println("Cannot find users with domain" + domain);
           return null;
       } else {
           return users.stream().map(x -> String.format("%s %s", x.getUsername(), x.getEmail())).collect(Collectors.toList());
       }
    }

}
