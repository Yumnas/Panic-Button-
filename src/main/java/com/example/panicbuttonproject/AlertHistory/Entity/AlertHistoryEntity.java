package com.example.panicbuttonproject.AlertHistory.Entity;

import com.example.panicbuttonproject.EmergencyContacts.Entity.UserEmergencyContact;
import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Alert_History")
public class AlertHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "panic_alert_id", nullable = false)
    private PanicAlert panicAlert;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private UserEmergencyContact contact;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime deliveredAt;

    private String LocationLink;
}
