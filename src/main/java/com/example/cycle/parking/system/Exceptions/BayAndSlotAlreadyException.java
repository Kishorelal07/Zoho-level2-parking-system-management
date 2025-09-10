package com.example.cycle.parking.system.Exceptions;

import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;

import java.util.List;

public class BayAndSlotAlreadyException extends RuntimeException{
    private final List<BaySlotDto> availableSlots;
    private final String freedAt;

    public BayAndSlotAlreadyException(String message, String freedAt, List<BaySlotDto> availableSlots) {
        super(message);
        this.freedAt = freedAt;
        this.availableSlots = availableSlots;
    }

    public List<BaySlotDto> getAvailableSlots() {
        return availableSlots;
    }

    public String getFreedAt() {
        return freedAt;
    }
}
