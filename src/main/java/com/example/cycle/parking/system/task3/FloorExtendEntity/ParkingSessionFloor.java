package com.example.cycle.parking.system.task3.FloorExtendEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Task3ParkingSession")
@Table(name = "task3_parking_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSessionFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userQr;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private String status;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;
}
