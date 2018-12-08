package mostwanted.domain.dtos.races;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceSeedDto {

    @XmlElement
    @NotNull
    private Integer laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElement(name = "entries")
    private EntryRoodSeedDto entryRoodSeedDto;

    public RaceSeedDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public EntryRoodSeedDto getEntryRoodSeedDto() {
        return entryRoodSeedDto;
    }

    public void setEntryRoodSeedDto(EntryRoodSeedDto entryRoodSeedDto) {
        this.entryRoodSeedDto = entryRoodSeedDto;
    }
}
