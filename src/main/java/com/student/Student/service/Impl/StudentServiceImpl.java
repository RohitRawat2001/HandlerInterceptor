package com.student.Student.service.Impl;

import com.student.Student.dto.UserRequest;
import com.student.Student.entity.Course;
import com.student.Student.entity.Student;
import com.student.Student.repository.CourseRepository;
import com.student.Student.repository.StudentRepository;
import com.student.Student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public UserRequest createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);

        UserRequest userRequest = new UserRequest();
        userRequest.setName(savedStudent.getName());
        savedStudent.getCourses()
                .forEach(c->c.setName(courseRepository.findById(c.getId())
                        .orElseThrow(()->new RuntimeException("Course not found")).getName()));
        userRequest.setCourses(savedStudent.getCourses());
        return userRequest;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> all = studentRepository.findAll();
        return all;
    }


    @Override
    public Student addCoursesToStudent(Long studentId, List<Long> courseIds) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }

        Student student = optionalStudent.get();
        List<Course> courses = courseRepository.findAllById(courseIds);

        if (courses.isEmpty()) {
            throw new IllegalArgumentException("No courses found with the provided IDs.");
        }
        student.getCourses().addAll(courses);
        return studentRepository.save(student);
    }
}
