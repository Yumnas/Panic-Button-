package com.example.panicbuttonproject.PanicAlertButton.Service;

import com.example.panicbuttonproject.AlertHistory.Entity.AlertHistoryEntity;
import com.example.panicbuttonproject.AlertHistory.Entity.DeliveryStatus;
import com.example.panicbuttonproject.AlertHistory.Repository.AlertHistoryRepository;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    @Autowired
    private AlertHistoryRepository alertHistoryRepository;

    @Override
    public PanicAlert sendPanicAlert(PanicAlertRequest panicAlertRequest, String username) {
        //finding if user exists
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Built a panic alert object for this alert event
        //it takes message from panicAlertRequest - the requestClass we made
        PanicAlert alert = PanicAlert.builder()
                .message(panicAlertRequest.getMessage())
                .user(user)
                .localDateTime(LocalDateTime.now())
                .build();

        //saving the alert with repository methods
        PanicAlert savedAlert = panicAlertRepository.save(alert);

        //formatting so you get appropriate amount of seconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Map.of(
                "message", alert.getMessage(),
                "sentAt", alert.getLocalDateTime().format(formatter)
        );
        //listing userEmergencyContacts by their id so I can pass them in panic alert api
        List<UserEmergencyContact> contacts = userEmergencyRepository.findByUserId(user.getId());

        //Sending google map location
        String locationLink;

        //used if you have the exact longitude and latitude points which are often given
        //by mobile or web if u press alert button there
        if (panicAlertRequest.getLatitude() != null && panicAlertRequest.getLongitude() != null) {
            locationLink = "https://www.google.com/maps?q="
                    + panicAlertRequest.getLatitude()
                    + ","
                    + panicAlertRequest.getLongitude();
        //this will be used if u sent the exact location in string
        } else if (panicAlertRequest.getLocation() != null){
            //google maps link
            locationLink = "https://www.google.com/maps/search/?api=1&query="
                    //Converts the address into a URL-friendly format
                    //https://www.google.com/maps/search/?api=1&query=Islamabad+F-7+Markaz
                    + URLEncoder.encode(panicAlertRequest.getLocation(), StandardCharsets.UTF_8);
        } else {
            locationLink = "Location not provided";
        }
        for (UserEmergencyContact contact : contacts) {
            try {
                //message that will be seen in email, sent when panic button is triggered
                String subject = "🚨 Panic Alert from " + user.getUserName();
                String body = panicAlertRequest.getMessage()
                        + "<br><br>This alert was triggered at " + alert.getLocalDateTime().format(formatter)
                        + "<br><br>Location: <a href=\"" + locationLink + "\">View on Google Maps</a>"
                        + "<br><br>Please check on " + user.getUserName();

                emailService.sendEmail(contact.getContactEmail(), subject, body);
                //saving this info for alertHistoryEntity
                //shows when panicAlertApi will be called
                AlertHistoryEntity history = AlertHistoryEntity.builder()
                        .panicAlert(alert)
                        .user(user)
                        .contact(contact)
                        .status(DeliveryStatus.SENT)
                        .deliveredAt(LocalDateTime.now())
                        .build();
                alertHistoryRepository.save(history);
            } catch (Exception e) {
                AlertHistoryEntity history = AlertHistoryEntity.builder()
                        .panicAlert(alert)
                        .user(user)
                        .contact(contact)
                        .status(DeliveryStatus.FAILED)
                        .deliveredAt(LocalDateTime.now())
                        .build();
                alertHistoryRepository.save(history);
            }
            System.out.println("Alert sent to contacts: " + contacts);
        }
        return savedAlert;
    }
}
