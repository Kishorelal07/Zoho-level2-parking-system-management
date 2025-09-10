package com.example.cycle.parking.system.Dto;

public class BaySlotDto {

    private String bay;
    private String slot;

    public BaySlotDto(String bay, String slot) {
        this.bay = bay;
        this.slot = slot;
    }

    public String getBay() { return bay; }
    public String getSlot() { return slot; }
}
