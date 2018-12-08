package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryRoodSeedDto {

    @XmlElement(name = "entry")
    private EntrySeedDto[] entrySeedDtos;

    public EntryRoodSeedDto() {
    }

    public EntrySeedDto[] getEntrySeedDtos() {
        return entrySeedDtos;
    }

    public void setEntrySeedDtos(EntrySeedDto[] entrySeedDtos) {
        this.entrySeedDtos = entrySeedDtos;
    }
}
