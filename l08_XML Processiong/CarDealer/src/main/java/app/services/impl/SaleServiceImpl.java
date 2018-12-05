package app.services.impl;

import app.dtos.views.CarViewNoParts;
import app.dtos.views.SaleView;
import app.entities.Car;
import app.entities.Customer;
import app.entities.Sale;
import app.repositories.CarRepository;
import app.repositories.CustomerRepository;
import app.repositories.SaleRepository;
import app.services.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Sale sale) {
        this.saleRepository.save(sale);
    }

    @Override
    public void insertRandomData() {
        double[] discounts = {0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        List<Car> allCars = this.carRepository.findAll();
        List<Customer> allCustomers = this.customerRepository.findAll();
        Random random = new Random();
        Set<Car> used = new HashSet<>();
        for (int i = 0; i < 150; i++) {
            Sale sale = new Sale();
            int randomIndex = random.nextInt(discounts.length);
            sale.setDiscount(discounts[randomIndex]);

            randomIndex = random.nextInt(allCars.size());
            Car car = allCars.get(randomIndex);
            while (used.contains(car)) {
                randomIndex = random.nextInt(allCars.size());
                car = allCars.get(randomIndex);
            }
            used.add(car);
            sale.setCar(allCars.get(randomIndex));
            randomIndex = random.nextInt(allCustomers.size());
            sale.setCustomer(allCustomers.get(randomIndex));
            this.save(sale);
        }
    }

    @Override
    public List<SaleView> getAllSaleDetails() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleView> saleViews = new ArrayList<>();
        for (Sale sale : sales) {
            SaleView saleView = new SaleView();
            CarViewNoParts carView = this.mapper.map(sale.getCar(), CarViewNoParts.class);
            saleView.setCar(carView);
            saleView.setCustomerName(sale.getCustomer().getName());
            saleView.setDiscount(sale.getDiscount());
            saleView.setPrice(sale.getCar().getPrice());
            BigDecimal priceWithDiscount = saleView.getPrice()
                    .multiply(BigDecimal.valueOf(1.00 - saleView.getDiscount()));
            saleView.setPriceWithDiscount(priceWithDiscount);
            saleViews.add(saleView);
        }
        return saleViews;
    }
}
