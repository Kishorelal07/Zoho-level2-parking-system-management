package com.example.cycle.parking.system.Mapper;

import com.example.cycle.parking.system.Dto.ParkingSessionCarDto;
import com.example.cycle.parking.system.task4.CarParkingEntity.ParkingSessionCar;

public class ParkingMapperCar {

    public static ParkingSessionCarDto toDto(ParkingSessionCar session) {
        ParkingSessionCarDto dto = new ParkingSessionCarDto();
        dto.setSessionId(session.getId());
        dto.setUserQr(session.getUserQr());
        dto.setVehicleNumber(session.getVehicleNumber());
        dto.setCheckInTime(session.getCheckInTime());
        dto.setCheckOutTime(session.getCheckOutTime());
        dto.setStatus(session.getStatus());

        if (session.getSlot() != null) {
            dto.setSlotNumber(session.getSlot().getSlotNumber());
            if (session.getSlot().getBay() != null) {
                dto.setBayName(session.getSlot().getBay().getBayName());
            }
        }

        return dto;
    }
}
