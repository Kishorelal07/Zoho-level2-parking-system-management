package com.example.cycle.parking.system.Dto;

import lombok.Data;

@Data
public class CheckInDto {

    private String vehicleNumber;

    private String slot;

    private String bay;

}
