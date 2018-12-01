package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.CarDto;
import car_dealer.domain.dtos.queringDtos.CarsPartsDto;
import car_dealer.domain.dtos.queringDtos.OrderedCarsDto;
import car_dealer.domain.dtos.queringDtos.PartDto;
import car_dealer.domain.dtos.seedDtos.CarsSeedDto;
import car_dealer.domain.entities.Car;
import car_dealer.domain.entities.Part;
import car_dealer.repository.CarRepository;
import car_dealer.repository.PartRepository;
import car_dealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCars(CarsSeedDto[] carsSeedDtos) {
        StringBuilder sb = new StringBuilder();

        for (CarsSeedDto carsSeedDto : carsSeedDtos) {
            if (!this.validatorUtil.isValid(carsSeedDto)){

                sb.append("Not valid").append(System.lineSeparator());
                continue;
            }

            Car carEntity = this.modelMapper.map(carsSeedDto, Car.class);
            carEntity.setParts(this.getRandomParts());

            this.carRepository.saveAndFlush(carEntity);
        }
    }


    private Set<Part> getRandomParts() {
        Set<Part> parts = new HashSet<>();
        Random rnd = new Random();

        List<Part> partEntities = this.partRepository.findAll();

        int length = rnd.nextInt(10) + 10;

        for (int i = 0; i < length; i++) {
            int partIndex = rnd.nextInt((int) (this.partRepository.count() - 1)) + 1;

            parts.add(partEntities.get(partIndex));
        }

        return parts;
    }

    @Override
    public List<OrderedCarsDto> orderedCars() {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<OrderedCarsDto> orderedCarsDtos = new ArrayList<>();

        for (Car car : cars) {
            OrderedCarsDto  carDto = this.modelMapper.map(car, OrderedCarsDto.class);
            orderedCarsDtos.add(carDto);
        }

        return orderedCarsDtos;
    }

    @Override
    public List<CarsPartsDto> getAllCarsWithTheirParts() {
        List<Car> cars = this.carRepository.findAll();
        List<CarsPartsDto> carsPartsDtos = new ArrayList<>();

        for (Car car : cars) {
            CarsPartsDto carsPartsDto = new CarsPartsDto();
            CarDto carDto = this.modelMapper.map(car, CarDto.class);
            carsPartsDto.setCarDto(carDto);

            for (Part part : car.getParts()) {
                PartDto partDto = this.modelMapper.map(part, PartDto.class);
                carsPartsDto.getPartDtoList().add(partDto);
            }
            carsPartsDtos.add(carsPartsDto);
        }
        return carsPartsDtos;
    }


}
