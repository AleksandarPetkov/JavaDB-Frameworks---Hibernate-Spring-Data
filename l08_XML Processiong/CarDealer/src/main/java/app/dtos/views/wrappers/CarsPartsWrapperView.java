package app.dtos.views.wrappers;

import app.dtos.views.CarView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsPartsWrapperView implements Serializable {
    @XmlElement(name = "car")
    private List<CarView> cars;

    public CarsPartsWrapperView() {
        this.cars = new ArrayList<>();
    }

    public List<CarView> getCars() {
        return cars;
    }

    public void setCars(List<CarView> cars) {
        this.cars = cars;
    }
}
