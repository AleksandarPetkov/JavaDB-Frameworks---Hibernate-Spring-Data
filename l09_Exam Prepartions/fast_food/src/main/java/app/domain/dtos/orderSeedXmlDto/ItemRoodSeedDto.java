package app.domain.dtos.orderSeedXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemRoodSeedDto {

    @XmlElement(name = "item")
    private ItemSeedDto[] itemSeedDtos;

    public ItemRoodSeedDto() {
    }

    public ItemSeedDto[] getItemSeedDtos() {
        return itemSeedDtos;
    }

    public void setItemSeedDtos(ItemSeedDto[] itemSeedDtos) {
        this.itemSeedDtos = itemSeedDtos;
    }
}
