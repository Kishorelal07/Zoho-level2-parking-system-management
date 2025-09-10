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
public class FloorCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;
    private int capacity;
    private boolean securityFloor;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<BayCar> bays;
}