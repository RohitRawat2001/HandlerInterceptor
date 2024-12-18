package com.student.Student.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentScoreDetails {
    private String subjectName;
    private double totalMarks;
    private double obtainedMarks;
}
