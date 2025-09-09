package com.example.panicbuttonproject.PanicAlertButton.Controller;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.EmergencyContacts.Repository.UserEmergencyRepository;
import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import com.example.panicbuttonproject.PanicAlertButton.Request.PanicAlertRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Response.BaseResponse;
import com.example.panicbuttonproject.PanicAlertButton.Response.PanicAlertResponse;
import com.example.panicbuttonproject.PanicAlertButton.Service.PanicAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PanicAlertController {

    @Autowired
    private PanicAlertService panicAlertService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEmergencyRepository userEmergencyRepository;

    @PostMapping("/panic")
    public BaseResponse<?> sendPanicAlert(@RequestBody PanicAlertRequest request,
                                                   Authentication authentication) {
        try {
            if (authentication == null) {
                return new PanicAlertResponse("401", "User not authenticated", null);
            }

            String username = authentication.getName();
            PanicAlert alert = panicAlertService.sendPanicAlert(request, username);
            UserEntity user = userRepository.findByUserName(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            List<UserEmergencyContact> contacts = userEmergencyRepository.findByUserId(user.getId());

            List<Map<String, Object>> contactList = contacts.stream()
                    .map(c -> Map.<String, Object>of(
                            "id", (Object) c.getId(),
                            "name", (Object) c.getContactName(),
                            "number", (Object) c.getContactNumber()
                    ))
                    .collect(Collectors.toList());

            return new BaseResponse<>("200", "Panic Alert Sent Successfully",
                    Map.of("alertId", alert.getId(), "sentTo", contactList));

        } catch (RuntimeException ex) {
            return new PanicAlertResponse("400", ex.getMessage(), null);
        }
    }
}
