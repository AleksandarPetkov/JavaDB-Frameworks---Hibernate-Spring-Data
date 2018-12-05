package app.services.api;

import app.dtos.bindings.CustomerDto;
import app.dtos.views.CustomerByBirthdateView;
import app.dtos.views.CustomerPurchasesView;
import app.entities.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    void saveAll(List<CustomerDto> customers);

    List<CustomerByBirthdateView> getAllSortedByBirthDate();

    List<CustomerPurchasesView> getAllCustomersWithPurchases();
}
