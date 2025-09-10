package com.example.cycle.parking.system.task4.CarParkingService.Impl;

import com.example.cycle.parking.system.Dto.ParkingSessionCarDto;
import com.example.cycle.parking.system.Mapper.ParkingMapperCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.BayCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.ParkingSessionCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;
import com.example.cycle.parking.system.task4.CarParkingRepository.BayCarRepository;
import com.example.cycle.parking.system.task4.CarParkingRepository.ParkingSessionCarRepository;
import com.example.cycle.parking.system.task4.CarParkingRepository.SlotCarRepository;
import com.example.cycle.parking.system.task4.CarParkingService.ParkingServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("carParkingService")
public class ParkingServiceCarImpl implements ParkingServiceCar {

    @Autowired
    private ParkingSessionCarRepository sessionRepository;

    @Autowired
    private SlotCarRepository slotRepository;

    @Autowired
    private BayCarRepository bayRepository;

    @Override
    public ParkingSessionCarDto checkIn(Long slotId, String vehicleNumber, String userQr) {
        SlotCar slot = slotRepository.findById(slotId).orElseThrow();
        if (!"FREE".equals(slot.getStatus())) {
            throw new RuntimeException("Slot is already occupied");
        }

        ParkingSessionCar session = new ParkingSessionCar();
        session.setUserQr(userQr);
        session.setVehicleNumber(vehicleNumber);
        session.setCheckInTime(LocalDateTime.now());
        session.setStatus("CHECKED_IN");
        session.setSlot(slot);

        slot.setStatus("OCCUPIED");
        slotRepository.save(slot);

        return ParkingMapperCar.toDto(sessionRepository.save(session));
    }

    @Override
    public ParkingSessionCarDto checkOut(Long sessionId) {
        ParkingSessionCar session = sessionRepository.findById(sessionId).orElseThrow(
                () -> new RuntimeException("Session not found")
        );

        session.setCheckOutTime(LocalDateTime.now());
        session.setStatus("CHECKED_OUT");

        SlotCar slot = session.getSlot();
        slot.setStatus("FREE");
        slotRepository.save(slot);

        return ParkingMapperCar.toDto(sessionRepository.save(session));
    }

    @Override
    public List<BayCar> getBaysByFloor(int floor) {
        return bayRepository.findByFloor(floor);
    }

    @Override
    public List<SlotCar> getFreeSlots() {
        return slotRepository.findByStatus("FREE");
    }
}