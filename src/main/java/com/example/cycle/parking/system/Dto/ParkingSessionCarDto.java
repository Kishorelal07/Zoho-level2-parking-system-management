package com.example.cycle.parking.system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSessionCarDto {

    private Long sessionId;
    private String userQr;
    private String vehicleNumber;
    private String slotNumber;
    private String bayName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String status;
}