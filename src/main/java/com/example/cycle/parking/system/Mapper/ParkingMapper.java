package com.example.cycle.parking.system.Mapper;

import com.example.cycle.parking.system.Dto.BayDto;
import com.example.cycle.parking.system.Dto.ParkingSessionDto;
import com.example.cycle.parking.system.Dto.SlotDto;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;

public class ParkingMapper {

    public static ParkingSessionDto toDto(ParkingSessionFloor session) {
        if (session == null) return null;

        ParkingSessionDto dto = new ParkingSessionDto();
        dto.setId(session.getId());
        dto.setVehicleNumber(session.getVehicleNumber());
        dto.setStatus(session.getStatus());

        Slot slot = session.getSlot();
        if (slot != null) {
            SlotDto slotDto = new SlotDto();
            slotDto.setSlotNumber(slot.getSlotNumber());

            Bay bay = slot.getBay();
            if (bay != null) {
                BayDto bayDto = new BayDto();
                bayDto.setBayName(bay.getBayName());
                bayDto.setFloor(bay.getFloor());
                slotDto.setBay(bayDto);
            }

            dto.setSlot(slotDto);
        }

        return dto;
    }
}