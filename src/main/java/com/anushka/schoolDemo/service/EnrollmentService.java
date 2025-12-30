package com.anushka.schoolDemo.service;

import com.anushka.schoolDemo.dto.CourseResponse;
import com.anushka.schoolDemo.dto.EnrollmentRequest;
import com.anushka.schoolDemo.dto.EnrollmentResponse;
import com.anushka.schoolDemo.dto.StudentResponse;
import com.anushka.schoolDemo.entity.Course;
import com.anushka.schoolDemo.entity.Enrollment;
import com.anushka.schoolDemo.entity.Student;
import com.anushka.schoolDemo.exception.ResourceNotFoundException;
import com.anushka.schoolDemo.repository.CourseRepository;
import com.anushka.schoolDemo.repository.EnrollmentRepository;
import com.anushka.schoolDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentResponse enroll(EnrollmentRequest request) {

        if (enrollmentRepository.existsByStudentStudentIdAndCourseCourseId(
                request.getStudentId(),
                request.getCourseId()
        )) {
            throw new IllegalStateException("Student is already enrolled in this course.");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id:" + request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + request.getCourseId()));

        Enrollment enrollment = new Enrollment(student, course);
        Enrollment saved = enrollmentRepository.save(enrollment);

        return new EnrollmentResponse(
                student.getStudentId(),
                student.getFirstName() + " " + student.getLastName(),
                course.getCourseId(),
                course.getCourseName(),
                saved.getEnrolledDate()
        );
    }

    public List<CourseResponse> getCoursesOfStudent(Long studentId){

        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id:" + studentId));

        return enrollmentRepository.findByStudentStudentId(studentId)
                .stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse();
                    return new CourseResponse(
                            course.getCourseId(),
                            course.getCourseName(),
                            course.getTeacher().getTeacherId(),
                            "Dr. " + course.getTeacher().getFirstName()
                    );
                })
                .toList();
    }

    public List<StudentResponse> getStudentsOfCourse(Long courseId){

        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + courseId));

        return enrollmentRepository.findByCourseCourseId(courseId)
                .stream()
                .map(enrollment -> {
                    Student student = enrollment.getStudent();
                    return new StudentResponse(
                            student.getStudentId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getEmail(),
                            student.getDateOfBirth()
                    );

                })
                .toList();
    }
}
