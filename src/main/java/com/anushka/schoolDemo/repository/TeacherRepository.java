package com.anushka.schoolDemo.repository;

import com.anushka.schoolDemo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
