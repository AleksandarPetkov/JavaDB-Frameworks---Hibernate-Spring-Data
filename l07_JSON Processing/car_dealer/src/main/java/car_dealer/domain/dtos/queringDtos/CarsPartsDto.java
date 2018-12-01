package car_dealer.domain.dtos.queringDtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CarsPartsDto {

    @Expose
    private CarDto carDto;

    @Expose
    private List<PartDto> partDtoList;

    public CarsPartsDto() {
        this.partDtoList = new ArrayList<>();
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public List<PartDto> getPartDtoList() {
        return partDtoList;
    }

    public void setPartDtoList(List<PartDto> partDtoList) {
        this.partDtoList = partDtoList;
    }
}
