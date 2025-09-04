package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.ForgotPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.ResetPasswordRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/UpdateEmployee/{id}")
    public ResponseEntity<UserEntity> updateEmployee(@PathVariable("id") long id, @RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/DeleteUser/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") long id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", result));
    }

}
