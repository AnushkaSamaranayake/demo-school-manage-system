package com.anushka.schoolDemo.controller;

import com.anushka.schoolDemo.dto.EnrollmentRequest;
import com.anushka.schoolDemo.dto.EnrollmentResponse;
import com.anushka.schoolDemo.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponse enroll(
            @Valid @RequestBody EnrollmentRequest request
    ) {
        return enrollmentService.enroll(request);
    }

    @GetMapping
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @DeleteMapping
    public void unenroll(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        enrollmentService.uneroll(studentId, courseId);
    }
}
