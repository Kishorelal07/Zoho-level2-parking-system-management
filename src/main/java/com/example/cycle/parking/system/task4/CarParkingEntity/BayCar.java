package com.example.cycle.parking.system.task4.CarParkingEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BayCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bayName;
    private Integer floor;

    @OneToMany(mappedBy = "bay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SlotCar> slots;
}