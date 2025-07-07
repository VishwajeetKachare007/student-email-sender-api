package com.studentAPI.controller;

import com.studentAPI.dto.StudentDTO;
import com.studentAPI.service.StudentService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(@RequestBody StudentDTO student) throws MessagingException,IOException{

        String result = studentService.processStudentApplication(student);

        return ResponseEntity.ok(result);
    }
}