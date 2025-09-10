package com.example.cycle.parking.system.task2.securityEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TheftReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private String bay;
    private String slot;
    private LocalDateTime reportedAt;

    private String status;

    private String policeStation;
    private String reportNumber;
}