package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarSeedDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {
    private static final String CAR_FILE_PATH = "D:\\MostWanted\\src\\main\\resources\\files\\cars.json";

    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CAR_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder sb = new StringBuilder();

        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsFileContent, CarSeedDto[].class);
        for (CarSeedDto carDto : carSeedDtos) {
            if (!this.validationUtil.isValid(carDto)){
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racerEntity = this.racerRepository.findByName(carDto.getRacerName()).orElse(null);
            if (racerEntity == null){
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car carEntity = modelMapper.map(carDto, Car.class);
            carEntity.setRacer(racerEntity);
            this.carRepository.saveAndFlush(carEntity);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, carEntity.getBrand(), carEntity.getModel())).append(System.lineSeparator());

        }

        return sb.toString();
    }
}
