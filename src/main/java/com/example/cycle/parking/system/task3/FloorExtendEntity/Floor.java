package com.example.cycle.parking.system.task3.FloorExtendEntity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Floor {
    @Id
    @GeneratedValue
    private Long id;

    private int floorNumber;
    private int capacity;
    private boolean securityFloor;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<Bay> bays;
}