package com.example.panicbuttonproject.PanicAlertButton.Service;

import com.example.panicbuttonproject.PanicAlertButton.Request.PanicAlertRequest;
import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;

public interface PanicAlertService {
    PanicAlert sendPanicAlert(PanicAlertRequest panicAlertRequest, String username );
}
