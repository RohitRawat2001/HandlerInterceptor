package com.student.Student.service;

import com.student.Student.entity.Course;

import java.util.List;

public interface CourseService {
     Course addCourse(Course course);
     List<Course> getAllCourses();

     Course findById(Long id);
}
