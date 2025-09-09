package com.example.panicbuttonproject.PanicAlertButton.Repository;

import com.example.panicbuttonproject.PanicAlertButton.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanicAlertRepository extends JpaRepository<PanicAlert, Long> {
    List<PanicAlert> findByUser(UserEntity user);
    @Modifying
    @Transactional
    @Query("DELETE FROM PanicAlert p WHERE p.user.id = :userId")
    void deleteByUserId(@Param("userId") long userId);

}
