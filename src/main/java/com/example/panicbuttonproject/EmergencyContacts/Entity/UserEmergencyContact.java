package com.example.panicbuttonproject.EmergencyContacts.Entity;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_emergency_contact")
public class UserEmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contactName;
    private String contactNumber;
    private String contactEmail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
