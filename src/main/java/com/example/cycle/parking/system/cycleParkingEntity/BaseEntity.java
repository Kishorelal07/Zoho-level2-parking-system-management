package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseEntity {
    @Column(name = "parked_at",updatable = false)
    private LocalDateTime parked_at;
    @Column(name = "vacated_at",updatable = false)
    private LocalDateTime vacated_at;
}
