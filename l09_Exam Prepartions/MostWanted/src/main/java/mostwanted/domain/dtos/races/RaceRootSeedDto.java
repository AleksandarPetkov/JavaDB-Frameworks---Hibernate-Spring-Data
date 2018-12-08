package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceRootSeedDto {

    @XmlElement(name = "race")
    private RaceSeedDto[] raceSeedDto;

    public RaceRootSeedDto() {
    }

    public RaceSeedDto[] getRaceSeedDto() {
        return raceSeedDto;
    }

    public void setRaceSeedDto(RaceSeedDto[] raceSeedDto) {
        this.raceSeedDto = raceSeedDto;
    }
}
