package com.example.panicbuttonproject.UserLoginAndRegistration.Request;

import jakarta.persistence.Column;

public class RegisterRequest {
    private String userName;
    private String password;
    private long idCardNumber;
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(long idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
