package com.example.panicbuttonproject.EmergencyContacts.Response;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;

public class UserEmergencyResponse extends BaseResponse<UserEmergencyContact>{
    public UserEmergencyResponse(String responseCode, String responseDec, UserEmergencyContact data) {
        super(responseCode, responseDec, data);
    }
}
