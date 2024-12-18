package com.student.Student.repository;

import com.student.Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
