package app;

import app.dtos.bindings.*;
import app.dtos.views.*;
import app.dtos.views.wrappers.*;
import app.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private SaleService saleService;
    private CustomerService customerService;
    private PartService partService;
    private SupplierService supplierService;
    private CarService carService;

    @Autowired
    public Terminal(SaleService saleService, CustomerService customerService, PartService partService, SupplierService supplierService, CarService carService) {
        this.saleService = saleService;
        this.customerService = customerService;
        this.partService = partService;
        this.supplierService = supplierService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedDatabase();

        //Problem 1
        //this.getAllCustomersOrderedByBirthdate();

        //Problem 2
        //this.getCarsFromMakeToyota();

        //Problem 3
        //this.getAllLocalSuppliers();

        //Problem 4
        //this.getAllCarsWithTheirParts();

        //Problem 5
        //this.getAllCustomersWithPurchases();

        //Problem 6
        //this.getAllSaleDetails();

    }

    private void getAllSaleDetails() throws IOException, JAXBException {
        List<SaleView> sales = this.saleService.getAllSaleDetails();
        SaleWrapperView salesView = new SaleWrapperView();
        salesView.setSales(sales);
        JAXBContext jaxbContext = JAXBContext.newInstance(SaleWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(salesView, new File("./src/main/resources/output/sales-details.xml"));
    }

    private void getAllCustomersWithPurchases() throws IOException, JAXBException {
        List<CustomerPurchasesView> customers = this.customerService.getAllCustomersWithPurchases();
        CustomerPurchasesWrapperView customersView = new CustomerPurchasesWrapperView();
        customersView.setCustomers(customers);
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerPurchasesWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customersView, new File("./src/main/resources/output/customers-purchases.xml"));
    }

    private void getAllCarsWithTheirParts() throws IOException, JAXBException {
        List<CarView> cars = this.carService.getAllCarsWithTheirParts();
        CarsPartsWrapperView carsView = new CarsPartsWrapperView();
        carsView.setCars(cars);
        JAXBContext jaxbContext = JAXBContext.newInstance(CarsPartsWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsView, new File("./src/main/resources/output/cars-parts.xml"));
    }

    private void getAllLocalSuppliers() throws IOException, JAXBException {
        List<SupplierView> suppliers = this.supplierService.getAllLocalSuppliers();
        SupplierWrapperView suppliersView = new SupplierWrapperView();
        suppliersView.setSuppliers(suppliers);
        JAXBContext jaxbContext = JAXBContext.newInstance(SupplierWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(suppliersView, new File("./src/main/resources/output/local-suppliers.xml"));
    }

    private void getCarsFromMakeToyota() throws IOException, JAXBException {
        List<CarByMakeView> cars = this.carService.getCarsByMake("Toyota");
        CarsByMakeWrapperView carsView = new CarsByMakeWrapperView();
        carsView.setCars(cars);
        JAXBContext jaxbContext = JAXBContext.newInstance(CarsByMakeWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsView, new File("./src/main/resources/output/toyota-cars.xml"));
    }

    private void getAllCustomersOrderedByBirthdate() throws IOException, JAXBException {
        List<CustomerByBirthdateView> customers = this.customerService.getAllSortedByBirthDate();
        CustomerBirthdayWrapperView customersView = new CustomerBirthdayWrapperView();
        customersView.setCustomers(customers);
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerBirthdayWrapperView.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customersView, new File("./src/main/resources/output/customers-by-birthdate.xml"));
    }

    private void seedDatabase() throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SupplierWrapperDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream inputStream = new FileInputStream("./src/main/resources/files/suppliers.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        SupplierWrapperDto suppliers = (SupplierWrapperDto) unmarshaller.unmarshal(reader);
        this.supplierService.saveAll(suppliers.getSuppliers());

        jaxbContext = JAXBContext.newInstance(PartWrapperDto.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        inputStream = new FileInputStream("./src/main/resources/files/parts.xml");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        PartWrapperDto parts = (PartWrapperDto) unmarshaller.unmarshal(reader);
        this.partService.saveAll(parts.getParts());

        jaxbContext = JAXBContext.newInstance(CarWrapperDto.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        inputStream = new FileInputStream("./src/main/resources/files/cars.xml");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        CarWrapperDto cars = (CarWrapperDto) unmarshaller.unmarshal(reader);
        this.carService.saveAll(cars.getCars());

        jaxbContext = JAXBContext.newInstance(CustomerWrapperDto.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        inputStream = new FileInputStream("./src/main/resources/files/customers.xml");
        reader = new BufferedReader(new InputStreamReader(inputStream));
        CustomerWrapperDto customers = (CustomerWrapperDto) unmarshaller.unmarshal(reader);
        this.customerService.saveAll(customers.getCustomers());

        this.saleService.insertRandomData();

    }
}
