package com.example.panicbuttonproject.PanicAlertButton.Service;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.EmergencyContacts.Repository.UserEmergencyRepository;
import com.example.panicbuttonproject.PanicAlertButton.Request.PanicAlertRequest;
import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.PanicAlertButton.Repository.PanicAlertRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class PanicAlertServiceImpl implements PanicAlertService {

    @Autowired
    private PanicAlertRepository panicAlertRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEmergencyRepository userEmergencyRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public PanicAlert sendPanicAlert(PanicAlertRequest panicAlertRequest, String username ){
    UserEntity user = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    PanicAlert alert = PanicAlert.builder()
            .message(panicAlertRequest.getMessage())
            .user(user)
            .localDateTime(LocalDateTime.now())
            .build();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Map.of(
                "message", alert.getMessage(),
                "sentAt", alert.getLocalDateTime().format(formatter)
    );
    List<UserEmergencyContact> contacts = userEmergencyRepository.findByUserId(user.getId());
        // Loop through and send alert
        for (UserEmergencyContact contact :  contacts) {
            String subject = "🚨 Panic Alert from " + user.getUserName();
            String body = panicAlertRequest.getMessage()
                    + "<br><br>This alert was triggered at " + alert.getLocalDateTime().format(formatter)
                    + "<br><br>Location: " + panicAlertRequest.getLocation()
                    + "<br><br>Please check on " + user.getUserName();

            emailService.sendEmail(contact.getContactEmail(), subject, body);
        }
    System.out.println("Alert sent to contacts: " + contacts);
    return panicAlertRepository.save(alert);
    }

}
