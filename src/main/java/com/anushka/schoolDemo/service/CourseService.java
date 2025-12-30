package com.anushka.schoolDemo.service;

import com.anushka.schoolDemo.dto.CourseCreateRequest;
import com.anushka.schoolDemo.dto.CourseResponse;
import com.anushka.schoolDemo.entity.Course;
import com.anushka.schoolDemo.entity.Teacher;
import com.anushka.schoolDemo.exception.ResourceNotFoundException;
import com.anushka.schoolDemo.repository.CourseRepository;
import com.anushka.schoolDemo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getCourseName(),
                course.getTeacher().getTeacherId(),
                course.getTeacher().getFirstName()
        );
    }

    public CourseService (
            CourseRepository courseRepository,
            TeacherRepository teacherRepository
    ) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public CourseResponse createCourse(CourseCreateRequest request){
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher not found with that id:" + request.getTeacherId()
                        )
                );

        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setTeacher(teacher);

        Course saved = courseRepository.save(course);
        return toResponse(saved);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CourseResponse getById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with that id" + id
                )
        );
        return toResponse(course);
    }

    public List<CourseResponse> getCoursesByTeacherId(Long teacherId){
        return courseRepository.findByTeacherTeacherId(teacherId)
                .stream()
                .map(this::toResponse)
                .toList();
    }


}
