package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacersSeedDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RacerServiceImpl implements RacerService {
    private static final String RACERS_FILE_PATH = "D:\\MostWanted\\src\\main\\resources\\files\\racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder sb = new StringBuilder();

        RacersSeedDto[] racersSeedDto = this.gson.fromJson(racersFileContent, RacersSeedDto[].class);

        for (RacersSeedDto racerDto : racersSeedDto) {
            if (!this.validationUtil.isValid(racerDto)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racerEntity = this.racerRepository.findByName(racerDto.getName()).orElse(null);
            if (racerEntity != null) {
                sb.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town homeTown = this.townRepository.findByName(racerDto.getHomeTown()).orElse(null);
            if (homeTown == null) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            racerEntity = this.modelMapper.map(racerDto, Racer.class);
            racerEntity.setHomeTown(homeTown);

            this.racerRepository.saveAndFlush(racerEntity);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racerEntity.getName())).append(System.lineSeparator());

        }

        return sb.toString();
    }

    @Override
    public String exportRacingCars() {
        return null;
    }
}
