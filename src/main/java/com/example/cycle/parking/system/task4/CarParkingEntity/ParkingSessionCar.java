package com.example.cycle.parking.system.task4.CarParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "CarParkingSession")
@Table(name = "car_parking_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSessionCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userQr;
    private String vehicleNumber;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String status; // CHECKED_IN / CHECKED_OUT

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private SlotCar slot;
}