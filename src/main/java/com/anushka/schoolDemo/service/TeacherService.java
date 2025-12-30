package com.anushka.schoolDemo.service;

import com.anushka.schoolDemo.dto.TeacherCreateRequest;
import com.anushka.schoolDemo.dto.TeacherResponse;
import com.anushka.schoolDemo.entity.Teacher;
import com.anushka.schoolDemo.exception.ResourceNotFoundException;
import com.anushka.schoolDemo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private TeacherResponse toResponse(Teacher teacher){
        return new TeacherResponse(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail()
        );
    }

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public TeacherResponse createTeacher(TeacherCreateRequest request){
        Teacher teacher = new Teacher();

        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());

        Teacher saved = teacherRepository.save(teacher);

        return toResponse(saved);
    }

    public List<TeacherResponse> getAllTeachers(){
        return teacherRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TeacherResponse getById(Long id){
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id:" + id));

        return toResponse(teacher);
    }

    public long getTeacherCount(){
        return teacherRepository.count();
    }


}
