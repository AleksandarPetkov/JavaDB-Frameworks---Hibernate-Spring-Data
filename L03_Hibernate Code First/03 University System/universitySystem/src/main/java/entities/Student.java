package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "average_grade")
    private double averageGrade;

    @Column(name = "attendance")
    private double attendance;

    @ManyToMany(mappedBy = "students", targetEntity = Course.class)
    private Set<Course> courses;

    public Student() {
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
