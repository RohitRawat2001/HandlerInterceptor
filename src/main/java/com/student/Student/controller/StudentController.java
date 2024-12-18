package com.student.Student.controller;

import com.student.Student.dto.UserRequest;
import com.student.Student.entity.Student;
import com.student.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<UserRequest> addStudent(@RequestBody Student student) {
        System.out.println("Received Student: " + student);
        UserRequest userRequest = studentService.createStudent(student);
        return ResponseEntity.ok(userRequest);
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/{studentId}/courses")
    public ResponseEntity<Student> addCoursesToStudent(
            @PathVariable Long studentId,
            @RequestBody List<Long> courseIds) {

        Student updatedStudent = studentService.addCoursesToStudent(studentId, courseIds);
        return ResponseEntity.ok(updatedStudent);
    }
}
