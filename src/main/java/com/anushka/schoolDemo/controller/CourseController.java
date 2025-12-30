package com.anushka.schoolDemo.controller;

import com.anushka.schoolDemo.dto.CourseCreateRequest;
import com.anushka.schoolDemo.dto.CourseResponse;
import com.anushka.schoolDemo.dto.StudentResponse;
import com.anushka.schoolDemo.service.CourseService;
import com.anushka.schoolDemo.service.EnrollmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public CourseController(
            CourseService courseService,
            EnrollmentService enrollmentService
    ) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public CourseResponse createCourse(
            @Valid @RequestBody CourseCreateRequest request
    ) {
        return courseService.createCourse(request);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @GetMapping("/{teacherId}/courses")
    public List<CourseResponse> getCoursesByTeacherId(@PathVariable Long teacherId) {
        return courseService.getCoursesByTeacherId(teacherId);
    }

    @GetMapping("/{id}/students")
    public List<StudentResponse> getStudentsOfCourse(
            @PathVariable Long id
    ) {
        return enrollmentService.getStudentsOfCourse(id);
    }

}
