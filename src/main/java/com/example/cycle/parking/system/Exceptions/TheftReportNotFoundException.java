package com.example.cycle.parking.system.Exceptions;

public class TheftReportNotFoundException extends RuntimeException{

    public TheftReportNotFoundException(String message) {
        super(message);
    }
}
