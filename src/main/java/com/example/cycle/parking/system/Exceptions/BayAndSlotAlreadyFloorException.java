package com.example.cycle.parking.system.Exceptions;

import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand;

import java.util.List;

public class BayAndSlotAlreadyFloorException extends RuntimeException{
    private final List<BaySlotDtoFloorExpand> availableSlots;
    private final String freedAt;

    public BayAndSlotAlreadyFloorException(String message, String freedAt, List<BaySlotDtoFloorExpand> availableSlots) {
        super(message);
        this.freedAt = freedAt;
        this.availableSlots = availableSlots;
    }

    public List<BaySlotDtoFloorExpand> getAvailableSlots() {
        return availableSlots;
    }

    public String getFreedAt() {
        return freedAt;
    }
}
