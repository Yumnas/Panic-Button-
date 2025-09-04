package com.example.panicbuttonproject.UserLoginAndRegistration.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "panic_alert")
public class PanicAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String message;
    private LocalDateTime localDateTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "users")
    private UserEntity user;
}
