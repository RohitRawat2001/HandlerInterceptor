package com.student.Student.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.*;
import com.student.Student.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public Logger logger = LoggerFactory.getLogger(PdfService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public ByteArrayInputStream createPdf(){

        logger.info("Create PDF Started: ");

        String title = "Welcome to PDF Generator - Create Your Custom PDF";
        String content = "This PDF demonstrates the dynamic generation of documents using Java and Spring Boot. "
                + "You can customize this content based on your requirements. "
                + "Feel free to explore and modify this template for your needs.";

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
        Paragraph titlePara = new Paragraph(title,titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18);
        Paragraph paragraph = new Paragraph(content,paraFont);
        paragraph.add(new Chunk(" Wow what a text"));

        // Table Section
        PdfPTable table = new PdfPTable(3); // 3 columns
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Age");

//        PdfPCell cell = new PdfPCell(new Phrase("Some Text"));
//        cell.setPadding(10f);  // Adds 10 points of padding inside each cell
//        table.addCell(cell);

        // Sample data for the table
        for (int i = 0; i < 5; i++) {
            table.addCell("1");
            table.addCell("John Doe");
            table.addCell("25");
        }

        // Add spacing before and after the table
        table.setSpacingBefore(20f);  // Add 20px spacing before the table
        table.setSpacingAfter(20f);   // Add 20px spacing after the table

        document.add(table);

        // List Section
        List list = new List(List.ORDERED); // Ordered list (1, 2, 3...)
        list.add(new ListItem("First item"));
        list.add(new ListItem("Second item"));
        list.add(new ListItem("Third item"));

        document.add(list);

        for(int i = 0 ; i < 100;i++){
            document.add(paragraph);
        }

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
