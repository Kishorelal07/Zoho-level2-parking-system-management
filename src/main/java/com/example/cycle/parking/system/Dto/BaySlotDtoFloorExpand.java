package com.example.cycle.parking.system.Dto;

public class BaySlotDtoFloorExpand {
    private Long bay;
    private String slot;
    private Integer floor;

    public BaySlotDtoFloorExpand(Long bay, String slot, Integer floor) {
        this.bay = bay;
        this.slot = slot;
        this.floor = floor;
    }

    public Long getBay() { return bay; }
    public String getSlot() { return slot; }
    public Integer getFloor() { return floor; }
}