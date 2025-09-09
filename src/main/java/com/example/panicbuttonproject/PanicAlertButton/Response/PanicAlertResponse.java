package com.example.panicbuttonproject.PanicAlertButton.Response;

import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.BaseResponse;

public class PanicAlertResponse extends BaseResponse<PanicAlert> {

    public PanicAlertResponse(String  responseCode, String responseDesc, PanicAlert panicAlert) {
        super(responseCode, responseDesc, panicAlert);
    }
}
