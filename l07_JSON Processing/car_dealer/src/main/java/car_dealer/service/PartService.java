package car_dealer.service;

import car_dealer.domain.dtos.seedDtos.PartsSeedDto;

public interface PartService {

    void seedParts(PartsSeedDto[] parts);
}
