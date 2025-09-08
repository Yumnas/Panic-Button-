package com.example.panicbuttonproject.UserLoginAndRegistration.Service.impl;

import com.example.panicbuttonproject.Configuration.JwtService;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.PanicAlertRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.RegisterRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.ResetPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.Role;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.EmailService;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PanicAlertRepository panicAlertRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, PanicAlertRepository panicAlertRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.panicAlertRepository = panicAlertRepository;
    }
    @Override
    public UserEntity registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())) {
            throw new RuntimeException("User already exists!");
        }
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserEntity user = UserEntity.builder().userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .idCardNumber(registerRequest.getIdCardNumber())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(registerRequest.getPassword())
                .build();

        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    @Override
    public String loginUser(String userName, String password) {
        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtService.generateToken(user.getUserName());
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserName(user.getUserName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setIdCardNumber(user.getIdCardNumber());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setCreatedAt(user.getCreatedAt());
        existingUser.setRole(user.getRole());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existingUser);
    }
    @Override
    public String deleteUser(Long id) {
        panicAlertRepository.deleteByUserId(id);
        userRepository.deleteById(id);
        return "Employee is deleted Successfully from the database for id " + id;
    }
    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest){
        UserEntity user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        //verify opt
        if (!resetPasswordRequest.getOpt().equals(user.getResetToken())) {
            throw new RuntimeException("Invalid or expired opt");
        }
        //update password
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        user.setResetToken(null);
        userRepository.save(user);
    }

    @Override
    public void generateAndSendOtp(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Generate OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Save OTP + expiry
        user.setResetToken(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5)); // expires in 5 min
        userRepository.save(user);

        // Send OTP via email
        emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp +
                "\n\nThis OTP will expire in 5 minutes.");
    }
    @Override
    public boolean verifyOtp(String email, String otp) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return otp.equals(user.getResetToken()) && user.getOtpExpiry() != null
                && user.getOtpExpiry().isAfter(LocalDateTime.now());
    }
}
