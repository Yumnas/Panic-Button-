package com.example.panicbuttonproject.UserLoginAndRegistration.Request;

public class PanicAlertRequest {
    private String message;
    private String location;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
