package com.example.cycle.parking.system.Dto;


import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;

@Data
public class FreeSlotDtoCar {
    private Long bayId;
    private String slotNumber;
    private int floor;

    public FreeSlotDtoCar(Long bayId, String slotNumber, int floor) {
        this.bayId = bayId;
        this.slotNumber = slotNumber;
        this.floor = floor;
    }

    // getters and setters
}