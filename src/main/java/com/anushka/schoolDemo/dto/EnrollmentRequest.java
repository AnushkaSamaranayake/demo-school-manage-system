package com.anushka.schoolDemo.dto;

import jakarta.validation.constraints.NotNull;

public class EnrollmentRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }
}
