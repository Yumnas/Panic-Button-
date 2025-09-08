package com.example.panicbuttonproject.EmergencyContacts.Service;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.EmergencyContacts.Request.AddContactRequest;
import com.example.panicbuttonproject.EmergencyContacts.Request.UpdateContactRequest;

import java.util.List;

public interface UserEmergencyService {
    UserEmergencyContact addContact(AddContactRequest request);
    String deleteContact(long id);
    UserEmergencyContact updateContacts(UpdateContactRequest updateContactRequest);
    List<UserEmergencyContact> getContactsForUser(Long userId);


}
