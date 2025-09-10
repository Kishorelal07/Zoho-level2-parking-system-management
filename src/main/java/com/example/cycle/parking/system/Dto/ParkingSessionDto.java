package com.example.cycle.parking.system.Dto;

import lombok.Data;

@Data
public class ParkingSessionDto {
    private Long id;
    private String vehicleNumber;
    private String status;
    private SlotDto slot;
}