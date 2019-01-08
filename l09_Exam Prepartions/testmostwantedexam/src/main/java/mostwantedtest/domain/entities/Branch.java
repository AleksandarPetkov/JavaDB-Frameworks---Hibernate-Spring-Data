package mostwantedtest.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "branches")
public class Branch extends BaseEntity{

    @Column(nullable = false)
    private String name;

    public Branch() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
