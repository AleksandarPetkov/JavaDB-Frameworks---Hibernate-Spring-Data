package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.races.EntrySeedDto;
import mostwanted.domain.dtos.races.RaceRootSeedDto;
import mostwanted.domain.dtos.races.RaceSeedDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    private static final String RACES_FILE_PATH = "D:\\MostWanted\\src\\main\\resources\\files\\races.xml";

    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        RaceRootSeedDto raceImportRootDto = this.xmlParser.parseXml(RaceRootSeedDto.class, RACES_FILE_PATH);
        for (RaceSeedDto raceDto : raceImportRootDto.getRaceSeedDto()) {

            District districtEntity = this.districtRepository.findByName(raceDto.getDistrictName()).orElse(null);
            if (!this.validationUtil.isValid(raceDto) || districtEntity == null){
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Race race = this.modelMapper.map(raceDto, Race.class);
            race.setDistrict(districtEntity);

            List<RaceEntry> raceEntries = new ArrayList<>();
            for (EntrySeedDto raceEntrySeedDto : raceDto.getEntryRoodSeedDto().getEntrySeedDtos()) {
                RaceEntry currentRaceEntry = this.raceEntryRepository.findById(raceEntrySeedDto.getId()).orElse(null);
                if (currentRaceEntry == null){
                    sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                //In RaceEntry i dont hava a RACE. And here i save it
                currentRaceEntry.setRace(race);
                //Keep then in collection because need first to save RACE
                raceEntries.add(currentRaceEntry);
            }

            //First save RACE in DB because i dont have it.
            Race raceId = this.raceRepository.saveAndFlush(race);

            //Накрая си пазя RaceEntry на които съм им сложил текущия Race
            this.raceEntryRepository.saveAll(raceEntries);

            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    raceId.getClass().getSimpleName(), raceId.getId())).append(System.lineSeparator());

        }

        return sb.toString();
    }
}
