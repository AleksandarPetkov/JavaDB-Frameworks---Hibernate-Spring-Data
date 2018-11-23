package productshop.service;

import productshop.domain.dtos.UserSeedDto;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);
}
