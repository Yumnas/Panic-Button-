package com.example.panicbuttonproject.UserLoginAndRegistration.Repository;

import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
    Optional<UserEntity> findByResetToken(String token);
    Optional<UserEntity> findByEmail(String email);
}
