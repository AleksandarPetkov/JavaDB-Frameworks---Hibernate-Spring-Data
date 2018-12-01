package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.LocalSupplierDto;
import car_dealer.domain.dtos.seedDtos.SuppliersSeedDto;

import car_dealer.domain.entities.Supplier;
import car_dealer.repository.SupplierRepository;
import car_dealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SuplierServiceImpl implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public SuplierServiceImpl(SupplierRepository supplierRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedSupplier(SuppliersSeedDto[] suppliersSeedDtos) {
        StringBuilder sb = new StringBuilder();

        for (SuppliersSeedDto suppliersSeedDto : suppliersSeedDtos) {
            if (!this.validatorUtil.isValid(suppliersSeedDto)){

                sb.append("Not valid").append(System.lineSeparator());
                continue;
            }

            Supplier supplierEntity = this.modelMapper.map(suppliersSeedDto, Supplier.class);

            this.supplierRepository.saveAndFlush(supplierEntity);
        }
    }

    @Override
    public List<LocalSupplierDto> getLocalSupplier() {
        StringBuilder sb = new StringBuilder();

        List<Supplier> suppliers = this.supplierRepository.getLocalSuppliers();
        List<LocalSupplierDto> localSupplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            LocalSupplierDto dto = this.modelMapper.map(supplier, LocalSupplierDto.class);
            int countOfParts = supplier.getParts().size();
            dto.setPartCount(countOfParts);

            localSupplierDtos.add(dto);
        }

        return localSupplierDtos;
    }
}
