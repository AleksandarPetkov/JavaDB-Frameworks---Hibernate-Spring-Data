package myprojectforusersystem.users.service;
import java.util.List;

public interface UserService {
    List<String> findUsersByDomain(String domain);
}
