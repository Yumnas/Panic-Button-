package com.example.panicbuttonproject.UserLoginAndRegistration.Response;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.PanicAlert;

public class PanicAlertResponse extends BaseResponse<PanicAlert> {

    public PanicAlertResponse(String  responseCode, String responseDesc, PanicAlert panicAlert) {
        super(responseCode, responseDesc, panicAlert);
    }
}
