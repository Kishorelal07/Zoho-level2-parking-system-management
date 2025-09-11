package com.example.cycle.parking.system.Exceptions;

import com.example.cycle.parking.system.Dto.BaySlotDtoCar;
import com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand;

import java.util.List;

public class BayAndSlotAlreadyForCar extends RuntimeException{
    private final List<BaySlotDtoCar> availableSlots;
    private final String freedAt;

    public BayAndSlotAlreadyForCar(String message, String freedAt, List<BaySlotDtoCar> availableSlots) {
        super(message);
        this.freedAt = freedAt;
        this.availableSlots = availableSlots;
    }

    public List<BaySlotDtoCar> getAvailableSlots() {
        return availableSlots;
    }

    public String getFreedAt() {
        return freedAt;
    }
}
