package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sba.sms.services.CourseService;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;
import java.util.HashSet;



/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */

@Entity
@Data

@Table(name = "student")
public class Student {

    @Id
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private Set<Course> courses = new HashSet<>();

    // Constructors
    public Student() {}

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Student(String email, String name, String password, Set<Course> courses) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.courses = courses;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }

    // Helper Method
    public void addCourse(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    @Override
    public String toString() {
        return "Student{" + "email='" + email + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}







