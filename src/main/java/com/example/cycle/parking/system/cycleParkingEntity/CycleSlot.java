package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CycleSlot {
    @Id
    @GeneratedValue
    private Long id;
    private String slotCode;
    private boolean occupied;
    private String bayCode;
    private LocalDateTime holdExpiry; // for temporary holds
}
