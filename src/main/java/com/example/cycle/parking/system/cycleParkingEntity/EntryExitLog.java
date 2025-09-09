package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class EntryExitLog {
    @Id
    @GeneratedValue
    private Long id;
    private String slotCode;
    private String userId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public EntryExitLog() {
    }
}
