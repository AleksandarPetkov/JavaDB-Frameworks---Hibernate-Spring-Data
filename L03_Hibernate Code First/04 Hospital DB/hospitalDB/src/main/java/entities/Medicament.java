package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @ManyToMany(mappedBy = "prescribedMedicaments", cascade = CascadeType.ALL)
    private Set<Patient> patients;

    public Medicament() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
