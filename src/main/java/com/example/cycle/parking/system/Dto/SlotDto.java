package com.example.cycle.parking.system.Dto;

import lombok.Data;

@Data
public class SlotDto {
    private String slotNumber;
    private BayDto bay;

}