package com.example.cycle.parking.system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaySlotDtoCar {

    private Long bayId;
    private String slotNumber;
    private Integer floor;

    public BaySlotDtoCar(Long bayId, String slotNumber, Integer floor) {
        this.bayId = bayId;
        this.slotNumber = slotNumber;
        this.floor = floor;
    }
}
