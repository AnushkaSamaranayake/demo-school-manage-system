package com.anushka.schoolDemo.controller;


import com.anushka.schoolDemo.dto.CourseResponse;
import com.anushka.schoolDemo.dto.StudentCreateRequest;
import com.anushka.schoolDemo.dto.StudentResponse;
import com.anushka.schoolDemo.service.EnrollmentService;
import com.anushka.schoolDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.anushka.schoolDemo.entity.Student;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    public StudentController(
            StudentService studentService,
            EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public StudentResponse createStudent(@Valid @RequestBody StudentCreateRequest request){
        return studentService.createStudent(request);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @GetMapping("{id}/courses")
    public List<CourseResponse> getCoursesOfStudent(@PathVariable Long id) {
        return enrollmentService.getCoursesOfStudent(id);
    }
}
