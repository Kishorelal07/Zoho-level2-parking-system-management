package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Bay {

    @Id
    @GeneratedValue
    private Long id;
    private String bayCode;
    private int capacity;
    private String side;
}
