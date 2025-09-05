package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Request.PanicAlertRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.BaseResponse;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.PanicAlertResponse;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.PanicAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.Map;

@RestController
public class PanicAlertController {

    @Autowired
    private PanicAlertService panicAlertService;

    @PostMapping("/panic")
    public BaseResponse<?> sendPanicAlert(@RequestBody PanicAlertRequest request,
                                                   Authentication authentication) {
        try {
            if (authentication == null) {
                return new PanicAlertResponse("401", "User not authenticated", null);
            }

            String username = authentication.getName();
            PanicAlert alert = panicAlertService.sendPanicAlert(request, username);

            return new BaseResponse<>("200", "Panic Alert Sent Successfully", Map.of("id", alert.getId()));

        } catch (RuntimeException ex) {
            return new PanicAlertResponse("400", ex.getMessage(), null);
        }
    }
}
