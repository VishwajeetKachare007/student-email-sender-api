package com.studentAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentEmailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentEmailSenderApplication.class, args);
        System.out.println("Application Started......!");
    }

}
