package alararestaurant.domain.dtos.orderdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemRootSeedDto {

    @XmlElement(name = "item")
    private ItemSeedDto[] itemSeedDtos;

    public ItemRootSeedDto() {
    }

    public ItemSeedDto[] getItemSeedDtos() {
        return itemSeedDtos;
    }

    public void setItemSeedDtos(ItemSeedDto[] itemSeedDtos) {
        this.itemSeedDtos = itemSeedDtos;
    }
}
