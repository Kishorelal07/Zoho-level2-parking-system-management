package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userQr;   // QR code ID
    private Instant checkInTime;
    private Instant checkOutTime;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;
}