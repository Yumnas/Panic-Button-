package com.example.panicbuttonproject.AlertHistory.Repository;

import com.example.panicbuttonproject.AlertHistory.Entity.AlertHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertHistoryRepository extends JpaRepository<AlertHistoryEntity, Long> {

}
