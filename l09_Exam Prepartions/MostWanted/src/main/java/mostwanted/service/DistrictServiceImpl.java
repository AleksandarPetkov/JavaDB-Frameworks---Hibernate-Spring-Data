package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictsSeedDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {
    private static final String DISTRICT_FILE_PATH = "D:\\MostWanted\\src\\main\\resources\\files\\districts.json";

    private final TownRepository townRepository;
    private final DistrictRepository districtRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(TownRepository townRepository, DistrictRepository districtRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICT_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder sb = new StringBuilder();
        DistrictsSeedDto[] districtsSeedDtos = this.gson.fromJson(districtsFileContent,  DistrictsSeedDto[].class);

        for (DistrictsSeedDto districtsDto : districtsSeedDtos) {
            if (!validationUtil.isValid(districtsDto)){
                sb.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District districtEntity = this.districtRepository.findByName(districtsDto.getName()).orElse(null);
            if (districtEntity != null){
                sb.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townEntiry = this.townRepository.findByName(districtsDto.getTownName()).orElse(null);

            districtEntity = this.modelMapper.map(districtsDto, District.class);
            districtEntity.setTown(townEntiry);
            this.districtRepository.saveAndFlush(districtEntity);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "District", districtEntity.getName())).append(System.lineSeparator());

        }
        return sb.toString();
    }
}
