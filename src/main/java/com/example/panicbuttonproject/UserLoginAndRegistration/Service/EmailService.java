package com.example.panicbuttonproject.UserLoginAndRegistration.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // "From" with display name
            helper.setFrom(new InternetAddress("yumnashabbir2@gmail.com", "MyApp Support"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // 'true' = HTML enabled

            mailSender.send(message);

            System.out.println("Mail sent successfully to: " + to);
        } catch (Exception e) {
            System.out.println("Error while sending mail: " + e.getMessage());
        }
    }
}