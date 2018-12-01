package car_dealer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "suppliers")
public class Supplier extends BaseEntity{

    @Column
    private String name;

    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
    private Set<Part> parts;

    public Supplier() {
    }


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


    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
