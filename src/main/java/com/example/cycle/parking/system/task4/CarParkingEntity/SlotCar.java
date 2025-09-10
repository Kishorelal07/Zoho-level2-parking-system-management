package com.example.cycle.parking.system.task4.CarParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "bay_id")
    private BayCar bay;
}