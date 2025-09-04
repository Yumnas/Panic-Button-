package com.example.panicbuttonproject.UserLoginAndRegistration.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private long idCardNumber;
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    //private String role = "USER";
    private LocalDateTime createdAt = LocalDateTime.now();
    // Password reset fields
    private String resetToken;
    private LocalDateTime resetTokenExpiry;
    private String otp;
    private LocalDateTime otpExpiry;
    //adding role
    @Enumerated(EnumType.STRING)
    private Role role;

}
