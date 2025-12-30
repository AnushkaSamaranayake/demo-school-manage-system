package com.anushka.schoolDemo.dto;

import java.time.LocalDate;

public class EnrollmentResponse {

    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private LocalDate enrolledDate;

    public EnrollmentResponse(
            Long studentId,
            String studentName,
            Long courseId,
            String courseName,
            LocalDate enrolledDate
    ) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledDate = enrolledDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }
}

