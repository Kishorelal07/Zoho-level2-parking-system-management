package com.example.cycle.parking.system.Exceptions;

public class VehicleNumberNotFoundException extends RuntimeException{
    public VehicleNumberNotFoundException(String message) {
        super(message);
    }
}
