package car_dealer.service;

import car_dealer.domain.dtos.seedDtos.PartsSeedDto;
import car_dealer.domain.entities.Part;
import car_dealer.domain.entities.Supplier;
import car_dealer.repository.PartRepository;
import car_dealer.repository.SupplierRepository;
import car_dealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;

        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedParts(PartsSeedDto[] parts) {
        StringBuilder sb = new StringBuilder();

        for (PartsSeedDto part : parts) {
            if (!this.validatorUtil.isValid(part)){

                sb.append("Not valid").append(System.lineSeparator());
                continue;
            }

            Part partEntity = this.modelMapper.map(part, Part.class);
            partEntity.setSupplier(this.getRandomSupplier());

            this.partRepository.saveAndFlush(partEntity);
        }
    }

    private Supplier getRandomSupplier(){
        Random random = new Random();
        return  this.supplierRepository.findById(random.nextInt((int)this.supplierRepository.count() - 1) + 1)
                .orElse(null);
    }
}
