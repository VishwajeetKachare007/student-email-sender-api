package com.studentAPI.service;


import com.studentAPI.dto.StudentDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface StudentService {
    String processStudentApplication(StudentDTO student) throws MessagingException, IOException;
}