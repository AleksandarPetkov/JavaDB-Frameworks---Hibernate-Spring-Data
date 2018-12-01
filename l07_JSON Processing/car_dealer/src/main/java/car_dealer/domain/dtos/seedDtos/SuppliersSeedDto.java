package car_dealer.domain.dtos.seedDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class SuppliersSeedDto {

    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public SuppliersSeedDto() {
    }

    @NotNull(message = "Name Cannot be null.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
