package car_dealer.service;

import car_dealer.domain.dtos.queringDtos.LocalSupplierDto;
import car_dealer.domain.dtos.seedDtos.SuppliersSeedDto;

import java.util.List;

public interface SupplierService {

    void seedSupplier(SuppliersSeedDto[] suppliersSeedDtos);

    List<LocalSupplierDto> getLocalSupplier();
}
