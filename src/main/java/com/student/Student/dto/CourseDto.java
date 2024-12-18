package com.student.Student.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private List<Long> courseIds;

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
