package app.services.impl;

import app.dtos.bindings.CustomerDto;
import app.dtos.views.CustomerByBirthdateView;
import app.dtos.views.CustomerPurchasesView;
import app.entities.Customer;
import app.repositories.CustomerRepository;
import app.services.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void saveAll(List<CustomerDto> customers) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        for (CustomerDto customerDto : customers) {
            Customer customer = this.mapper.map(customerDto, Customer.class);
            this.save(customer);
        }
    }

    @Override
    public List<CustomerByBirthdateView> getAllSortedByBirthDate() {
        List<Customer> customers = this.customerRepository.getAllByBirthdate();
        List<CustomerByBirthdateView> customerViews = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerByBirthdateView customerView = this.mapper.map(customer, CustomerByBirthdateView.class);
            customerViews.add(customerView);
        }
        return customerViews;
    }

    @Override
    public List<CustomerPurchasesView> getAllCustomersWithPurchases() {
        return this.customerRepository.getAllCustomersWithPurchases();
    }
}
