package com.example.panicbuttonproject.EmergencyContacts.Controller;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.EmergencyContacts.Request.AddContactRequest;
import com.example.panicbuttonproject.EmergencyContacts.Request.UpdateContactRequest;
import com.example.panicbuttonproject.EmergencyContacts.Response.BaseResponse;
import com.example.panicbuttonproject.EmergencyContacts.Service.UserEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class UserEmergencyController {

    @Autowired
    private UserEmergencyService userEmergencyService;

    @PostMapping("/AddContact")
    public BaseResponse<?> AddContacts(@RequestBody AddContactRequest request) {
        try {
            UserEmergencyContact contact = userEmergencyService.addContact(request);
            return new BaseResponse<>(
                    "200",
                    "Contact added successfully",
                    Map.of(
                            "id", contact.getId(),
                            "user", Map.of(
                                    "username", contact.getUser().getUserName()
                            )
                    )
            );
        } catch (Exception ex) {
            return new BaseResponse<>("400", ex.getMessage(), null);
        }
    }
    @DeleteMapping("/delete/{id}")
    public BaseResponse<String> deleteContact(@PathVariable long id) {
        try {
            String result = userEmergencyService.deleteContact(id);
            return new BaseResponse<>(
                    "200", "Contact deleted successfully", result);
        } catch (Exception ex) {
            return new BaseResponse<>("400", ex.getMessage(), null);
        }
    }

    @PatchMapping("/update")
    public BaseResponse<?> updateContact(@RequestBody UpdateContactRequest request) {
        try {
            UserEmergencyContact contact = userEmergencyService.updateContacts(request);
            return new BaseResponse<>(
                    "200",
                    "Contact added successfully",
                    Map.of(
                            "updated id", contact.getId(),
                            "updated contactName", contact.getContactName(),
                            "updated contactNumber", contact.getContactNumber(),
                            "updated contactEmail", contact.getContactEmail(),
                            "user", Map.of(
                                    "username", contact.getUser().getUserName()
                            )
                    )
            );
        } catch (Exception ex) {
            return new BaseResponse<>("400", ex.getMessage(), null);
        }
    }
    @GetMapping("/user/{userId}")
            public BaseResponse<List<UserEmergencyContact>> getContactsForUser(@PathVariable Long userId) {
                try {
                    List<UserEmergencyContact> contacts = userEmergencyService.getContactsForUser(userId);
                    return new BaseResponse<>("200", "Contacts fetched successfully", contacts);
                } catch (Exception ex) {
            return new BaseResponse<>("400", ex.getMessage(), null);
        }
    }
}


