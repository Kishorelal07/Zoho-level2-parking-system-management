package com.example.cycle.parking.system.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BayAndSlotAlreadyException.class)
    public ResponseEntity<ErrorResponse> handleBayAndSlotAlreadyException(BayAndSlotAlreadyException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                ex.getFreedAt(),
                ex.getAvailableSlots()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VehicleNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehicleNumberNotFoundException(VehicleNumberNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null,
                null
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    static class ErrorResponse {
        private int status;
        private String message;
        private LocalDateTime timestamp;
        private String freedAt;
        private Object availableSlots;

        public ErrorResponse(int status, String message, LocalDateTime timestamp, String freedAt, Object availableSlots) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
            this.freedAt = freedAt;
            this.availableSlots = availableSlots;
        }

        public int getStatus() { return status; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getFreedAt() { return freedAt; }
        public Object getAvailableSlots() { return availableSlots; }
    }
}
