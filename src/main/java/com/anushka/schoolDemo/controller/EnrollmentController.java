package com.anushka.schoolDemo.controller;

import com.anushka.schoolDemo.dto.EnrollmentRequest;
import com.anushka.schoolDemo.dto.EnrollmentResponse;
import com.anushka.schoolDemo.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
