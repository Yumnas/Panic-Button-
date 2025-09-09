package com.example.panicbuttonproject.EmergencyContacts.Repository;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserEmergencyRepository extends JpaRepository<UserEmergencyContact, Long> {
    List<UserEmergencyContact> findByUserId(Long userId);
}
