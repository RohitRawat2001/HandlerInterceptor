package com.student.Student.service;

import com.student.Student.dto.UserRequest;
import com.student.Student.entity.Student;

import java.util.List;

public interface StudentService {

    UserRequest createStudent(Student student);
    List<Student> getAllStudents();
    Student addCoursesToStudent(Long studentId, List<Long> courseIds);

}
