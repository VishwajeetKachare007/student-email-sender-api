package com.studentAPI.service;

import com.studentAPI.dto.StudentDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String processStudentApplication(StudentDTO student)
            throws MessagingException ,IOException{

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(student.getEmail());
        helper.setSubject("Application Received - Confirmation");
        String htmlContent = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<h2>🎓 Application Received Successfully</h2>" +
                "<p>Dear <strong>" + student.getStudentName() + "</strong>,</p>" +
                "<p>We are pleased to inform you that your admission application has been received by <strong>Sanskriti School of Engineering</strong>.</p>" +
                "<h3>📋 Application Summary:</h3>" +
                "<table style='border-collapse: collapse;'>"+
                "<tr><td><strong>Full Name:</strong></td><td>" + student.getStudentName() + "</td></tr>" +
                "<tr><td><strong>Date of Birth:</strong></td><td>" + student.getDateOfBirth() + "</td></tr>" +
                "<tr><td><strong>Father's Name:</strong></td><td>" + student.getFatherName() + "</td></tr>" +
                "<tr><td><strong>Mobile Number:</strong></td><td>" + student.getStudentMobile() + "</td></tr>" +
                "<tr><td><strong>Branch Applied:</strong></td><td>" + student.getBranch() + "</td></tr>" +
                "<tr><td><strong>Category:</strong></td><td>" + student.getCategory() + "</td></tr>" +
                "<tr><td><strong>Hall Ticket No:</strong></td><td>" + student.getApeapcetHallTicket() + "</td></tr>" +
                "<tr><td><strong>Rank:</strong></td><td>" + student.getApeapcetRank() + "</td></tr>" +
                "</table>" +
                "<br/>" +student.toString()+
                "</body></html>";

        helper.setText(htmlContent, true);


        mailSender.send(message);

        return "Application Submitted and Email Sent!";
    }

}
