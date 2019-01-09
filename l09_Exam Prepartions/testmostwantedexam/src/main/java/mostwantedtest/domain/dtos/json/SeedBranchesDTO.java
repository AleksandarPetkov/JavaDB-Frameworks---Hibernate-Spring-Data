package mostwantedtest.domain.dtos.json;


import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;

public class SeedBranchesDTO {

    @Expose
    @NotNull
    private String name;

    public SeedBranchesDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
