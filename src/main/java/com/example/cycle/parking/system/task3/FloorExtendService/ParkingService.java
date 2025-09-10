package com.example.cycle.parking.system.task3.FloorExtendService;

import com.example.cycle.parking.system.Dto.ParkingSessionDto;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;

import java.util.List;

public interface ParkingService {

    // Check-in API
    ParkingSessionDto checkIn(Long slotId, String vehicleNumber, String userQr);

    // Check-out API
    ParkingSessionDto checkOut(Long sessionId);

    List<Bay> getBaysByFloor(int floor);

    List<Slot> getFreeSlots();
}
