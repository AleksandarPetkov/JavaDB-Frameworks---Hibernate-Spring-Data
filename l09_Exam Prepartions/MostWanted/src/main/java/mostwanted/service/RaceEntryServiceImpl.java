package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryRootSeedDto;
import mostwanted.domain.dtos.raceentries.RaceEntrySeedDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private static final String RACE_ENTRY_FILE_PATH = "D:\\MostWanted\\src\\main\\resources\\files\\race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRY_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        RaceEntryRootSeedDto raceEntryRootSeedDto = this.xmlParser.parseXml(RaceEntryRootSeedDto.class, RACE_ENTRY_FILE_PATH);

        for (RaceEntrySeedDto raceEntryDto : raceEntryRootSeedDto.getRaceEntrySeedDtos()) {
            if (!this.validationUtil.isValid(raceEntryDto)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car carEntity = this.carRepository.findById(raceEntryDto.getCarId()).orElse(null);
            Racer racerEntity = this.racerRepository.findByName(raceEntryDto.getRacer()).orElse(null);
            if (racerEntity == null || carEntity == null) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            RaceEntry raceEntry = this.modelMapper.map(raceEntryDto, RaceEntry.class);
            raceEntry.setCar(carEntity);
            raceEntry.setRacer(racerEntity);
            raceEntry.setRace(null);
            raceEntry = this.raceEntryRepository.saveAndFlush(raceEntry);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "RaceEntry", raceEntry.getId())).append(System.lineSeparator());

        }

        return sb.toString();
    }
}
