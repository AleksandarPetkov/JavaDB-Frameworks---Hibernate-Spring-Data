package car_dealer.web.controller;

import car_dealer.domain.dtos.queringDtos.CarsPartsDto;
import car_dealer.domain.dtos.queringDtos.LocalSupplierDto;
import car_dealer.domain.dtos.queringDtos.OrderedCarsDto;
import car_dealer.domain.dtos.queringDtos.OrderedCustomersDto;
import car_dealer.domain.dtos.seedDtos.CarsSeedDto;
import car_dealer.domain.dtos.seedDtos.CustomersSeedDto;
import car_dealer.domain.dtos.seedDtos.PartsSeedDto;
import car_dealer.domain.dtos.seedDtos.SuppliersSeedDto;
import car_dealer.service.*;
import car_dealer.util.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.List;


@Controller
public class CarDealerController implements CommandLineRunner {

    private static final String SUPPLIERS_FILE_PATH = "D:\\car_dealer\\src\\main\\resources\\files\\suppliers.json";
    private static final String PARTS_FILE_PATH = "D:\\car_dealer\\src\\main\\resources\\files\\parts.json";
    private static final String CAR_FILE_PATH = "D:\\car_dealer\\src\\main\\resources\\files\\cars.json";
    private static final String CUSTOMERS_FILE_PATH = "D:\\car_dealer\\src\\main\\resources\\files\\customers.json";

    private final SupplierService supplierService;
    private final CustomerService customerService;
    private final CarService carService;
    private final PartService partService;
    private final SaleService saleService;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public CarDealerController(SupplierService supplierService, CustomerService customerService, CarService carService, PartService partService, SaleService saleService, FileUtil fileUtil, Gson gson) {
        this.supplierService = supplierService;
        this.customerService = customerService;
        this.carService = carService;
        this.partService = partService;
        this.saleService = saleService;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {
//        this.seedSuppliers();
//        this.seedParts();
//        this.seedCars();
//        this.seedCustomers();
//        this.seedSales();
//        this.sortCustomers();
//        this.orderedCars();
//        this.getLocalSuppliers();
        this.getCarWithParts();
    }

    private void seedSuppliers() throws IOException {
        String fileInfo = this.fileUtil.readFile(SUPPLIERS_FILE_PATH);

        SuppliersSeedDto[] suppliersSeedDto = this.gson.fromJson(fileInfo, SuppliersSeedDto[].class);

        this.supplierService.seedSupplier(suppliersSeedDto);
    }

    private void seedParts() throws IOException {
        String fileInfo = this.fileUtil.readFile(PARTS_FILE_PATH);

        PartsSeedDto[] parts = this.gson.fromJson(fileInfo,  PartsSeedDto[].class);

        this.partService.seedParts(parts);
    }


    private void seedCars() throws IOException {
        String fileInfo = this.fileUtil.readFile(CAR_FILE_PATH);

        CarsSeedDto[] carsSeedDtos = this.gson.fromJson(fileInfo, CarsSeedDto[].class);

        this.carService.seedCars(carsSeedDtos);
    }

    private void seedCustomers() throws IOException {
        String fileInfo = this.fileUtil.readFile(CUSTOMERS_FILE_PATH);

        CustomersSeedDto[] customersSeedDtos = this.gson.fromJson(fileInfo,   CustomersSeedDto[].class);

        this.customerService.seedCustomers(customersSeedDtos);
    }

    private void seedSales(){
        this.saleService.seedSales();
    }

    private void sortCustomers() throws IOException {
        List<OrderedCustomersDto> orderedCustomersDtos = this.customerService.orderedCustomers();

        String gsonFormat = this.gson.toJson(orderedCustomersDtos);
        System.out.println(gsonFormat);
    }

    private void orderedCars(){
        List<OrderedCarsDto> orderedCarsDtos = this.carService.orderedCars();

        String gsonFormat = this.gson.toJson(orderedCarsDtos);
        System.out.println(gsonFormat);
    }

    private void getLocalSuppliers(){
        List<LocalSupplierDto> localSupplierDtos = this.supplierService.getLocalSupplier();

        String gsonFormat = this.gson.toJson(localSupplierDtos);
        System.out.println(gsonFormat);
    }

    private void getCarWithParts(){
        List<CarsPartsDto> carsPartsDtos = this.carService.getAllCarsWithTheirParts();

        String gsonFormat = this.gson.toJson(carsPartsDtos);
        System.out.println(gsonFormat);
    }
}
