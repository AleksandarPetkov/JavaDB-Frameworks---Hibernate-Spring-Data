package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.CarsPartsDto;
import car_dealer.domain.dtos.queringDtos.OrderedCarsDto;
import car_dealer.domain.dtos.seedDtos.CarsSeedDto;

import java.util.List;

public interface CarService {

    void seedCars(CarsSeedDto[] carsSeedDtos);

    List<OrderedCarsDto> orderedCars();

    List<CarsPartsDto> getAllCarsWithTheirParts();
}
