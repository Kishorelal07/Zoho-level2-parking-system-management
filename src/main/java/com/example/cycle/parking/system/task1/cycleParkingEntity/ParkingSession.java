package com.example.cycle.parking.system.task1.cycleParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userQr;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String slot;
    private String bay;
    private String status;
    @Column(name = "vehicle_number")
    private String vehicleNumber;
}
 