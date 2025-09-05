package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.BaseResponse;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/GetAllUsers")
    public BaseResponse<?> getAllUsers() {
        try {
            return new BaseResponse<>("200", "Users fetched successfully", userService.getAllUsers());
        } catch (RuntimeException ex) {
            return new BaseResponse<>("400", "Failed to fetch users: " + ex.getMessage(), null);
        }
    }
    @PatchMapping("/UpdateEmployee/{id}")
    public BaseResponse<?> updateEmployee(@PathVariable("id") long id, @RequestBody UserEntity user) {
        try {
            UserEntity updatedUser = userService.updateUser(id, user);
            return new BaseResponse<>("200", "User updated successfully", updatedUser);
        } catch (RuntimeException ex) {
            return new BaseResponse<>("400", "Failed to update user: " + ex.getMessage(), null);
        }
    }
    @DeleteMapping("/DeleteUser/{id}")
    public BaseResponse<?> deleteUser(@PathVariable("id") long id) {
        try {
            String result = userService.deleteUser(id);
            return new BaseResponse<>("200", result, null);
        } catch (RuntimeException ex) {
            return new BaseResponse<>("400", "Failed to delete user: " + ex.getMessage(), null);
        }
    }

}
