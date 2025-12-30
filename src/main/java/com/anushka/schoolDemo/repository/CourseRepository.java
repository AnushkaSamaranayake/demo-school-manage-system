package com.anushka.schoolDemo.repository;

import com.anushka.schoolDemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTeacherTeacherId(Long teacherId);
}
