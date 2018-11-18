package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Column
    private String email;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @OneToMany(targetEntity = Course.class, mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher() {
    }

    public String getEmail() {
        return email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


}
