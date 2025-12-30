package com.anushka.schoolDemo.dto;

public class CourseResponse {

    private Long id;
    private String courseName;
    private Long teacherId;
    private String teacherName;

    public CourseResponse(
            Long id,
            String courseName,
            Long teacherId,
            String teacherName
    ) {
        this.id = id;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
