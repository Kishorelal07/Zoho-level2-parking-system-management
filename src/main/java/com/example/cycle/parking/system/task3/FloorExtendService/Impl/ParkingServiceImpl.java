package com.example.cycle.parking.system.task3.FloorExtendService.Impl;

import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand;
import com.example.cycle.parking.system.Dto.ParkingSessionDto;
import com.example.cycle.parking.system.Exceptions.BayAndSlotAlreadyException;
import com.example.cycle.parking.system.Exceptions.BayAndSlotAlreadyFloorException;
import com.example.cycle.parking.system.Exceptions.SlotAlreadyOccupiedException;
import com.example.cycle.parking.system.Mapper.ParkingMapper;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;
import com.example.cycle.parking.system.task3.FloorExtendRepository.BayRepository;
import com.example.cycle.parking.system.task3.FloorExtendRepository.ParkingSessionRepository;
import com.example.cycle.parking.system.task3.FloorExtendRepository.SlotRepository;
import com.example.cycle.parking.system.task3.FloorExtendService.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("task3ParkingService")
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingSessionRepository sessionRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private BayRepository bayRepository;

    @Override
    public ParkingSessionDto checkIn(Long slotId, String vehicleNumber, String userQr) {
        Slot slot = slotRepository.findById(slotId).orElseThrow();
        if (!"FREE".equals(slot.getStatus())) {
            List<BaySlotDtoFloorExpand> freeSlots = sessionRepository.findFreeSlots();
            throw new BayAndSlotAlreadyFloorException(
                    "This Bay and Slot has been registered already! ",
                    "Will be freed at " + LocalDateTime.now()
                            .plusHours(4)
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    freeSlots
            );
        }

        ParkingSessionFloor session = new ParkingSessionFloor();
        session.setUserQr(userQr);
        session.setVehicleNumber(vehicleNumber);
        session.setCheckInTime(LocalDateTime.now());
        session.setStatus("CHECKED_IN");
        session.setSlot(slot);

        slot.setStatus("OCCUPIED");
        slotRepository.save(slot);

        return ParkingMapper.toDto(sessionRepository.save(session));
    }



    public ParkingSessionDto checkOut(Long sessionId) {
        ParkingSessionFloor session = sessionRepository.findById(sessionId).orElseThrow(
                () ->new SlotAlreadyOccupiedException("Slot may already checked out")
        );

        session.setCheckOutTime(LocalDateTime.now());
        session.setStatus("CHECKED_OUT");

        Slot slot = session.getSlot();
        slot.setStatus("FREE");
        slotRepository.save(slot);

        return ParkingMapper.toDto(sessionRepository.save(session));
    }

    @Override
    public List<Bay> getBaysByFloor(int floor) {
        return bayRepository.findByFloor(floor);
    }

    @Override
    public List<Slot> getFreeSlots() {
        return slotRepository.findByStatus("FREE");
    }
}
