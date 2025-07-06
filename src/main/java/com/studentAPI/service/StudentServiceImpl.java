package com.studentAPI.service;

import com.studentAPI.dto.StudentDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String processStudentApplication(StudentDTO student,
                                            MultipartFile sscMarkSheet,
                                            MultipartFile interMarkSheet,
                                            MultipartFile interTC,
                                            MultipartFile interStudyConduct,
                                            MultipartFile casteIncome,
                                            MultipartFile aadhar,
                                            MultipartFile ration,
                                            MultipartFile passportPhoto)
            throws MessagingException, IOException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

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
                "<p>📌 Our team will verify your documents shortly. Please keep an eye on your email for further updates.</p>" +
                "<p style='margin-top: 30px;'>Best Regards,<br/><strong>Admission Team</strong><br/>Sanskriti School of Engineering</p>" +
                "</body></html>";

        helper.setText(htmlContent, true);
        attachFile(helper, sscMarkSheet);
        attachFile(helper, interMarkSheet);
        attachFile(helper, interTC);
        attachFile(helper, interStudyConduct);
        attachFile(helper, casteIncome);
        attachFile(helper, aadhar);
        attachFile(helper, ration);
        attachFile(helper, passportPhoto);

        mailSender.send(message);

        return "Application Submitted and Email Sent!";
    }

    private void attachFile(MimeMessageHelper helper, MultipartFile file) throws MessagingException, IOException {
        if (file != null) {
            helper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
        }
    }
}
