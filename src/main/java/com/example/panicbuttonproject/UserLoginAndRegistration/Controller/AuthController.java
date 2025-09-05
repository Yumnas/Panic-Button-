package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.Request.ForgotPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.LoginRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.RegisterRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.ResetPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.BaseResponse;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public BaseResponse<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.loginUser(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            return new BaseResponse<>("200", "Login successful", Map.of("token", "Bearer " + token));
        } catch (RuntimeException ex) {
            return new BaseResponse<>("401", "Login failed: " + ex.getMessage(), null);
        }
    }
    @PostMapping("/register")
    public BaseResponse<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            UserEntity savedUser = userService.registerUser(registerRequest);
            return new BaseResponse<>("200", "User registered successfully",
                    Map.of("id", savedUser.getId(), "username", savedUser.getUserName()));

        } catch (RuntimeException ex) {
            return new BaseResponse<>("400", "Registration failed: " + ex.getMessage(), null);
        }
    }
    @PostMapping("/forgot-password")
    public BaseResponse<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        try{
            userService.generateAndSendOtp(forgotPasswordRequest.getEmail());
            return new BaseResponse<>("200", "OTP sent to your email", null);
        } catch (RuntimeException e) {
            return new BaseResponse<>("400", "Failed to send OTP " + e.getMessage(), null);
        }
    }
    @PostMapping("/reset-password")
    public BaseResponse<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try{
            userService.resetPassword(resetPasswordRequest);
            return new BaseResponse<>("200", "Password has been reset successfully", null);
        } catch (RuntimeException e) {
            return new BaseResponse<>("400", "Reset Password Failed " +  e.getMessage(), null);
        }
    }
}
