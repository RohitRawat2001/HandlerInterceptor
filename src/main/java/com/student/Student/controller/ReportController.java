package com.student.Student.controller;

import com.student.Student.service.Impl.ReportService;
import com.student.Student.service.StudentService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class ReportController {

//    private StudentService studentService;

    private final ReportService reportService;

    @GetMapping("/report/{reportFormat}")
    public String getReport(@PathVariable String reportFormat) throws JRException, FileNotFoundException {
        return reportService.exportReport(reportFormat);
    }

}
