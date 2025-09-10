package com.example.cycle.parking.system.Exceptions;

public class SlotAlreadyOccupiedException extends RuntimeException{

    public SlotAlreadyOccupiedException(String message) {
        super(message);
    }

}
