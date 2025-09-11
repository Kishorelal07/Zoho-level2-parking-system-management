package com.example.cycle.parking.system.task4.CarParkingService;

import com.example.cycle.parking.system.Dto.FreeSlotDtoCar;
import com.example.cycle.parking.system.Dto.ParkingSessionCarDto;
import com.example.cycle.parking.system.task4.CarParkingEntity.BayCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;

import java.util.List;

public interface ParkingServiceCar {
    ParkingSessionCarDto checkIn(Long slotId, String vehicleNumber, String userQr);
    ParkingSessionCarDto checkOut(Long sessionId);
    List<BayCar> getBaysByFloor(int floor);
    List<SlotCar> getFreeSlots();

    public List<FreeSlotDtoCar> getFreeSlotDetails();
}