package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.OrderedCustomersDto;
import car_dealer.domain.dtos.seedDtos.CustomersSeedDto;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomersSeedDto[] dtos);

    List<OrderedCustomersDto> orderedCustomers();
}
