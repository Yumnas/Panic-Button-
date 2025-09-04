package com.example.panicbuttonproject.UserLoginAndRegistration.Repository;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanicAlertRepository extends JpaRepository<PanicAlert, Long> {
    List<PanicAlert> findByUser(UserEntity user);

}
