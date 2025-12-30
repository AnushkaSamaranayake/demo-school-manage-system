package com.anushka.schoolDemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "enrollment")
@Getter
@Setter
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "enrolled_date")
    private LocalDate enrolledDate;

    public Enrollment() {}

    public Enrollment(
            Student student,
            Course course
    ) {
        this.student = student;
        this.course = course;
        this.id = new EnrollmentId(
                student.getStudentId(),
                course.getCourseId()
        );
        this.enrolledDate = LocalDate.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }
}
