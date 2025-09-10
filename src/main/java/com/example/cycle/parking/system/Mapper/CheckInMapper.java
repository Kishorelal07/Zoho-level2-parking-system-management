package com.example.cycle.parking.system.Mapper;

import com.example.cycle.parking.system.Dto.CheckInDto;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;

public class CheckInMapper {

    public static ParkingSession maptoCustomer(CheckInDto checkInDto, ParkingSession parkingSession){

        parkingSession.setBay(checkInDto.getBay());
        parkingSession.setSlot(checkInDto.getSlot());
        parkingSession.setVehicleNumber(checkInDto.getVehicleNumber());
        return parkingSession;

    }


}
