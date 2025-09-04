package com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass;

public class ResetPasswordRequest {
    private String email;
    private String opt;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public ResetPasswordRequest(String email, String opt, String newPassword) {
        this.email = email;
        this.opt = opt;
        this.newPassword = newPassword;
    }
}
