package com.example.panicbuttonproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PanicButtonProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicButtonProjectApplication.class, args);
        // The hashed password (from your database)
//        String hashedPassword = "$2a$10$ikzwurE/XwuJDsdbXhAV5.Jg839aIdAqq1IKh5Beib5G6jFvgQD9e";
//
//        // The password you want to check
//        String plainPassword = "pakistanZindabad8998";
//
//        // Create a PasswordEncoder
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        // Check if it matches
//        boolean matches = passwordEncoder.matches(plainPassword, hashedPassword);
//
//        if (matches) {
//            System.out.println("Password is correct!");
//        } else {
//            System.out.println("Password is incorrect!");
//        }
    }

}
