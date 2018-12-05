package app.dtos.views.wrappers;

import app.dtos.views.CarByMakeView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsByMakeWrapperView implements Serializable{
    @XmlElement(name = "car")
    private List<CarByMakeView> cars;

    public CarsByMakeWrapperView() {
        this.cars = new ArrayList<>();
    }

    public List<CarByMakeView> getCars() {
        return cars;
    }

    public void setCars(List<CarByMakeView> cars) {
        this.cars = cars;
    }
}
