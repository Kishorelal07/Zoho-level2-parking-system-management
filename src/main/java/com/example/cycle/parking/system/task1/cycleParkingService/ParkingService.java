package com.example.cycle.parking.system.task1.cycleParkingService;

import com.example.cycle.parking.system.Dto.CheckInDto;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;

public interface ParkingService {

    public void checkIn(CheckInDto checkInDto);

    public void checkOut(String vehicleNumber);
}
