package com.example.panicbuttonproject.UserLoginAndRegistration.Response;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;

public class UserResponse extends BaseResponse<UserEntity>{
    public UserResponse(String responseCode, String responseDesc, UserEntity user) {
        super(responseCode, responseDesc, user);
    }
}
