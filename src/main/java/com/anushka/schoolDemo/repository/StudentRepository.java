package com.anushka.schoolDemo.repository;

import com.anushka.schoolDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    public void deleteById(Long studentId);
}
