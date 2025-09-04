package com.example.panicbuttonproject.UserLoginAndRegistration.Controller;

import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.PanicAlertRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.PanicAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
public class PanicAlertController {

    @Autowired
    private PanicAlertService panicAlertService;

    @PostMapping("/panic")
    public ResponseEntity<String> sendPanicAlert(@RequestBody PanicAlertRequest request,
                                                 Authentication authentication) {
        String username = authentication.getName();
        panicAlertService.sendPanicAlert(request, username);
        return ResponseEntity.ok("Panic alert sent successfully!");
    }
}
