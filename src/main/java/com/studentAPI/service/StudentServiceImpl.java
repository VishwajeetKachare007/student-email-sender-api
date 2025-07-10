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
        helper.setSubject("🎓 Admission Confirmation - Sanskriti School of Engineering");
        String htmlContent =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif;'>" +
                        "<h2 style='color:#2e6c80;'>🎓 Application Received Successfully</h2>" +
                        "<p>Dear <strong>" + student.getStudentName() + "</strong>,</p>" +
                        "<p>We are pleased to inform you that your admission application has been received by <strong>Sanskriti School of Engineering</strong>.</p>" +
                        "<h3 style='margin-top: 30px;'>📋 Application Summary:</h3>" +
                        "<table style='border-collapse: collapse; width: 100%; font-size: 14px;'>"
                        + generateHtmlTable(student) +
                        "</table>" +
                        "<p style='margin-top:20px;'>Thank you for applying! We will review your application and get back to you soon.</p>" +
                        "<p style='color:gray;'>— Admissions Office, Sanskriti School of Engineering</p>" +
                        "</body></html>";

        helper.setText(htmlContent, true);


        mailSender.send(message);

        return "Application Submitted and Email Sent!";
    }
    private String generateHtmlTable(StudentDTO s) {
        return
                addRow("Full Name", s.getStudentName()) +
                        addRow("Gender", s.getGender()) +
                        addRow("Date of Birth", s.getDateOfBirth()) +
                        addRow("Father Name", s.getFatherName()) +
                        addRow("Mother Name", s.getMotherName()) +
                        addRow("Father Occupation", s.getFatherOccupation()) +
                        addRow("Student Mobile", s.getStudentMobile()) +
                        addRow("Parents Mobile", s.getParentsMobile()) +
                        addRow("Branch", s.getBranch()) +
                        addRow("Hall Ticket", s.getApeapcetHallTicket()) +
                        addRow("Rank", s.getApeapcetRank()) +
                        addRow("Category", s.getCategory()) +
                        addRow("Quota", s.getQuota()) +
                        addRow("Counselling Phase", s.getCounsellingPhase()) +
                        addRow("Last Institution", s.getLastStudiedInstitution()) +
                        addRow("Address", s.getDoorNo() + ", " + s.getStreetName() + ", " + s.getArea() + ", " + s.getLandmark() + ", " + s.getPincode()) +
                        addRow("Aadhar", s.getAadharNumber()) +
                        addRow("Scholarship Eligible", s.getEligibleForScholarship()) +
                        addRow("Scholarship Caste", s.getScholarshipCaste()) +
                        addRow("Scholarship Category", s.getScholarshipCategory()) +
                        addRow("Accommodation", s.getAccommodationType()) +
                        addRow("SSC Marks", s.getSscMarks()) +
                        addRow("SSC %", s.getSscPercentag()) +
                        addRow("Inter Marks", s.getInterMarks()) +
                        addRow("Inter %", s.getInterPercentage()) +
                        addRow("Diploma Marks", s.getDiplomaMarks()) +
                        addRow("Diploma %", s.getDiplomaPercentage()) +
                        addRow("College Fee", s.getCollegeFee()) +
                        addRow("Hostel/Bus Fee", s.getHostelBusFee()) +
                        addRow("Other Fee", s.getOtherFee()) +
                        addRow("Fee Concession", s.getFeeConsession());
    }
    private String addRow(String label, String value) {
        return "<tr style='border-bottom:1px solid #ccc;'>" +
                "<td style='padding:8px; font-weight:bold; width:200px;'>" + label + ":</td>" +
                "<td style='padding:8px;'>" + (value == null || value.isEmpty() ? "-" : value) + "</td>" +
                "</tr>";
    }

}
