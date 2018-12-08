package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryRootSeedDto {

    @XmlElement(name = "race-entry")
    private RaceEntrySeedDto[] raceEntrySeedDtos;

    public RaceEntryRootSeedDto() {
    }

    public RaceEntrySeedDto[] getRaceEntrySeedDtos() {
        return raceEntrySeedDtos;
    }

    public void setRaceEntrySeedDtos(RaceEntrySeedDto[] raceEntrySeedDtos) {
        this.raceEntrySeedDtos = raceEntrySeedDtos;
    }
}
