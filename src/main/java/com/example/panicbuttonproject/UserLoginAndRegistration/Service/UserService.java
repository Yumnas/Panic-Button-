package com.example.panicbuttonproject.UserLoginAndRegistration.Service;

import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.RegisterRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.ResetPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;

import java.util.List;

public interface UserService {
 UserEntity registerUser(RegisterRequest registerRequest);
 String loginUser(String userName, String password);
 List<UserEntity> getAllUsers();
 UserEntity updateUser(Long id, UserEntity user);
 String deleteUser(Long id);
 //String createPasswordResetToken(String email);
 //void resetPassword(String token, String newPassword);

 void resetPassword(ResetPasswordRequest resetPasswordRequest);

 // ✅ Add these
 void generateAndSendOtp(String email);
 boolean verifyOtp(String email, String otp);

}