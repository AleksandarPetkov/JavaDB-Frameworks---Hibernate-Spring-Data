package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String comments;

    @ManyToMany(mappedBy = "diagnoses", cascade = CascadeType.ALL)
    private Set<Patient> patients;

    public Diagnose() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComments() {
        return comments;
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
