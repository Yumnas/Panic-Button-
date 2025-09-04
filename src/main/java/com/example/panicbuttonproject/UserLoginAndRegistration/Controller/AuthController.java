package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.ForgotPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.LoginRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.RegisterRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.ResetPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(Map.of("token", "Bearer " + token));
    }
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        userService.generateAndSendOtp(forgotPasswordRequest.getEmail());
        return ResponseEntity.ok("OTP sent to your email");
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok("Password reset successful!");
    }
}
