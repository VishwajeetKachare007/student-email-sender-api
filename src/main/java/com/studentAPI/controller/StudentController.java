package com.studentAPI.controller;

import com.studentAPI.dto.StudentDTO;
import com.studentAPI.service.StudentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(
            @RequestPart("student") StudentDTO student,
            @RequestPart("sscMarkSheet") MultipartFile sscMarkSheet,
            @RequestPart("interMarkSheet") MultipartFile interMarkSheet,
            @RequestPart("interTC") MultipartFile interTC,
            @RequestPart("interStudyConduct") MultipartFile interStudyConduct,
            @RequestPart("casteIncome") MultipartFile casteIncome,
            @RequestPart("aadhar") MultipartFile aadhar,
            @RequestPart("ration") MultipartFile ration,
            @RequestPart("passportPhoto") MultipartFile passportPhoto
    ) throws MessagingException, IOException {

        String result = studentService.processStudentApplication(
                student,
                sscMarkSheet,
                interMarkSheet,
                interTC,
                interStudyConduct,
                casteIncome,
                aadhar,
                ration,
                passportPhoto
        );

        return ResponseEntity.ok(result);
    }
}