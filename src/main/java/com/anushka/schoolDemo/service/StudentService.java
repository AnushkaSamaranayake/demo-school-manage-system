package com.anushka.schoolDemo.service;

import com.anushka.schoolDemo.dto.CourseResponse;
import com.anushka.schoolDemo.dto.StudentCreateRequest;
import com.anushka.schoolDemo.dto.StudentResponse;
import com.anushka.schoolDemo.entity.Course;
import com.anushka.schoolDemo.entity.Student;
import com.anushka.schoolDemo.exception.ResourceNotFoundException;
import com.anushka.schoolDemo.repository.EnrollmentRepository;
import com.anushka.schoolDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth()
        );
    }

    public StudentService(
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository
    ){
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public StudentResponse createStudent(StudentCreateRequest request){
        Student student = new Student();

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setDateOfBirth(request.getDateOfBirth());

        Student saved = studentRepository.save(student);
        return toResponse(saved);
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id:" + id));

        return toResponse(student);
    }

    public long getStudentCount() {
        return studentRepository.count();
    }


}
