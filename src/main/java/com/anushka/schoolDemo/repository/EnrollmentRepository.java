package com.anushka.schoolDemo.repository;

import com.anushka.schoolDemo.entity.Enrollment;
import com.anushka.schoolDemo.entity.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    boolean existsByStudentStudentIdAndCourseCourseId(
            Long studentId,
            Long courseId
    );

    List<Enrollment> findByStudentStudentId(Long StudentId);
    List<Enrollment> findByCourseCourseId(Long CourseId);

    void deleteBy(EnrollmentId id);
}
