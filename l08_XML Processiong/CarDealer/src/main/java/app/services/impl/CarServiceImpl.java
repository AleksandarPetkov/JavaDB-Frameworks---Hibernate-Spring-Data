package app.services.impl;

import app.dtos.bindings.CarDto;
import app.dtos.views.CarByMakeView;
import app.dtos.views.CarView;
import app.dtos.views.PartView;
import app.entities.Car;
import app.entities.Part;
import app.repositories.CarRepository;
import app.repositories.PartRepository;
import app.services.api.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private PartRepository partRepository;
    private ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Car car) {
        this.carRepository.save(car);
    }

    @Override
    public void saveAll(List<CarDto> cars) {
        Random random = new Random();
        List<Part> allParts = this.partRepository.findAll();
        for (CarDto carDto : cars) {
            Car car = this.mapper.map(carDto, Car.class);
            int numberOfParts = random.nextInt(11) + 10;
            for (int i = 0; i < numberOfParts; i++) {
                int randomIndex = random.nextInt(allParts.size());
                car.addPart(allParts.get(randomIndex));
            }
            this.save(car);
        }
    }

    @Override
    public List<CarByMakeView> getCarsByMake(String make) {
        List<Car> cars = this.carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(make);
        List<CarByMakeView> carViews = new ArrayList<>();
        for (Car car : cars) {
            CarByMakeView carView = this.mapper.map(car, CarByMakeView.class);
            carViews.add(carView);
        }
        return carViews;
    }

    @Override
    public List<CarView> getAllCarsWithTheirParts() {
        List<Car> cars = this.carRepository.findAll();
        List<CarView> carPartsViews = new ArrayList<>();
        for (Car car : cars) {
            CarView carView = this.mapper.map(car, CarView.class);
            for (Part part : car.getParts()) {
                PartView partView = this.mapper.map(part, PartView.class);
                carView.getParts().add(partView);
            }
            carPartsViews.add(carView);
        }
        return carPartsViews;
    }
}
