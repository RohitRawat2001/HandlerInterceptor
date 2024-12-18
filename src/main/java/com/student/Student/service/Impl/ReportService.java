package com.student.Student.service.Impl;

import com.student.Student.dto.UserRequest;
import com.student.Student.entity.Student;
import com.student.Student.entity.StudentDetails;
import com.student.Student.entity.StudentScoreDetails;
import com.student.Student.repository.StudentRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private StudentRepository studentRepository;

    public String exportReport(String resportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Test app\\jasperReport\\Blank_A4.jasper";
//        List<Student> student = studentRepository.findAll();
       // Optional<Student> byId = studentRepository.findById(1);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);

        List<StudentDetails> studentDetails = new ArrayList<>();
        studentDetails.add(new StudentDetails("Raja","2023","ABCD","2022-2023"));
       // studentDetails.add(byId.get());

//        File file = ResourceUtils.getFile("classpath:Blank_A4.jrxml");
//        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(studentDetails);

        List<StudentScoreDetails> studentScoreDetails = new ArrayList<>();
        studentScoreDetails.add(new StudentScoreDetails("Subject1",100.0,80.0));
        studentScoreDetails.add(new StudentScoreDetails("Subject2",100.0,50.0));
        studentScoreDetails.add(new StudentScoreDetails("Subject3",100.0,60.0));
        studentScoreDetails.add(new StudentScoreDetails("Subject4",100.0,70.0));


        JRBeanCollectionDataSource tabledataSource = new JRBeanCollectionDataSource(studentScoreDetails);

        //create hashmap object  to store parameter
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("TABLE_DATA_SOURCE",tabledataSource);

        JasperPrint jp = JasperFillManager.fillReport(jasperReport,parameters,ds);

        if(resportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jp,path+"\\students.html");
        }

//        if(resportFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(jp,path+"\\students.pdf");
//        }

        JasperExportManager.exportReportToPdfFile(jp,"C:\\Test app\\generatedJasperReport\\StudentScoreCard.pdf");

        return "File Created success At Path"+path;
    }
}
