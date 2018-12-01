package car_dealer.domain.dtos.queringDtos;

import com.google.gson.annotations.Expose;

public class LocalSupplierDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private int partCount;

    public LocalSupplierDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartCount() {
        return partCount;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }
}
