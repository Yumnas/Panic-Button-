package com.example.panicbuttonproject.EmergencyContacts.Service;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.EmergencyContacts.Repository.UserEmergencyRepository;
import com.example.panicbuttonproject.EmergencyContacts.Request.AddContactRequest;
import com.example.panicbuttonproject.EmergencyContacts.Request.UpdateContactRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserEmergencyServiceImpl implements UserEmergencyService{

    private final UserEmergencyRepository userEmergencyRepository;
    private final UserRepository userRepository;

    public UserEmergencyServiceImpl(UserEmergencyRepository userEmergencyRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.userEmergencyRepository = userEmergencyRepository;
        this.userRepository = userRepository1;
    }

    @Override
    public UserEmergencyContact addContact(AddContactRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserEmergencyContact contact = UserEmergencyContact.builder()
                .contactEmail(request.getContactEmail())
                .contactNumber(request.getContactNumber())
                .user(user)
                .contactName(request.getContactName())
                .build();
        return userEmergencyRepository.save(contact);
    }

    @Override
    public String deleteContact(long id) {
        userEmergencyRepository.deleteById(id);
        return "Contact deleted Successfully for id " + id;
    }

    @Override
    public UserEmergencyContact updateContacts(UpdateContactRequest request) {
        UserEmergencyContact contact = userEmergencyRepository.findById(request.getContactId())
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.getUser().getId().equals(request.getUserId())) {
            throw new RuntimeException("This contact does not belong to the user");
        }
        if (request.getContactName() != null) {
            contact.setContactName(request.getContactName());
        }
        if (request.getContactNumber() != null) {
            contact.setContactNumber(request.getContactNumber());
        }
        if (request.getContactEmail() != null) {
            contact.setContactEmail(request.getContactEmail());
        }
        return userEmergencyRepository.save(contact);
    }
    @Override
    public List<UserEmergencyContact> getContactsForUser(Long userId) {
        return userEmergencyRepository.findAll();
    }
}
