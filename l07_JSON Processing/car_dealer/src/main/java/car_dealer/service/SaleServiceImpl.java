package car_dealer.service;

import car_dealer.domain.entities.Car;
import car_dealer.domain.entities.Customer;
import car_dealer.domain.entities.Sale;
import car_dealer.repository.CarRepository;
import car_dealer.repository.CustomerRepositoty;
import car_dealer.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService{

    private final CarRepository carRepository;
    private final CustomerRepositoty customerRepositoty;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(CarRepository carRepository, CustomerRepositoty customerRepositoty, SaleRepository saleRepository) {
        this.carRepository = carRepository;
        this.customerRepositoty = customerRepositoty;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seedSales() {
        Random random = new Random();

        Double[] discount =  {0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        List<Car> cars = this.carRepository.findAll();
        List<Customer> customers = this.customerRepositoty.findAll();

        for (int i = 0; i < 50; i++) {
            Sale sale = new Sale();

            int randomIndex = random.nextInt(discount.length);
            sale.setDiscount(discount[randomIndex]);

            randomIndex = random.nextInt(cars.size());
            sale.setCar(cars.get(randomIndex));

            randomIndex = random.nextInt(customers.size());
            sale.setCustomer(customers.get(randomIndex));

            this.saleRepository.saveAndFlush(sale);
        }
    }
}
