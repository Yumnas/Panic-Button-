package com.example.panicbuttonproject.UserLoginAndRegistration.Service;

import com.example.panicbuttonproject.UserLoginAndRegistration.Request.PanicAlertRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.PanicAlert;

public interface PanicAlertService {
    PanicAlert sendPanicAlert(PanicAlertRequest panicAlertRequest, String username );
}
