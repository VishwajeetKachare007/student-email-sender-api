package com.studentAPI.service;


import com.studentAPI.dto.StudentDTO;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService {
    String processStudentApplication(
            StudentDTO student,
            MultipartFile sscMarkSheet,
            MultipartFile interMarkSheet,
            MultipartFile interTC,
            MultipartFile interStudyConduct,
            MultipartFile casteIncome,
            MultipartFile aadhar,
            MultipartFile ration,
            MultipartFile passportPhoto
    ) throws MessagingException, IOException;
}