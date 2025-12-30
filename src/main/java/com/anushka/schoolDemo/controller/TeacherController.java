package com.anushka.schoolDemo.controller;

import com.anushka.schoolDemo.dto.TeacherCreateRequest;
import com.anushka.schoolDemo.dto.TeacherResponse;
import com.anushka.schoolDemo.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping
    public TeacherResponse createTeacher(@Valid @RequestBody TeacherCreateRequest request){
        return teacherService.createTeacher(request);
    }

    @GetMapping
    public List<TeacherResponse> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherResponse getById(@PathVariable Long id){
        return teacherService.getById(id);
    }
}
