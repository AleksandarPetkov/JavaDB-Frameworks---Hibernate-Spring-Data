package app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "positions")
public class Position extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String position;

    @OneToMany(mappedBy = "position", targetEntity = Employee.class)
    private List<Employee> employees;

    public Position() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
