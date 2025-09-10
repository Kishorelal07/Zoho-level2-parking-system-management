package com.example.cycle.parking.system.Dto;

import lombok.Data;

@Data
public class TheftReportDto {
    private String vehicleNumber;
    private String bay;
    private String slot;
    private String policeStation;
}
