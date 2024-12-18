package com.student.Student.dto;

import com.student.Student.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    private List<Course> courses;
}
