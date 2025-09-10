package com.example.cycle.parking.system.task4.CarParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheftReportCar {

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

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ParkingSessionCar parkingSession;
}