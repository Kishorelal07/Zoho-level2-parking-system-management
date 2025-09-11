package com.example.cycle.parking.system.task1.cycleParkingService.impl;

import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.Dto.CheckInDto;
import com.example.cycle.parking.system.Exceptions.BayAndSlotAlreadyException;
import com.example.cycle.parking.system.Exceptions.VehicleNumberNotFoundException;
import com.example.cycle.parking.system.Mapper.CheckInMapper;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;
import com.example.cycle.parking.system.task1.cycleParkingRepository.ParkingRepository;
import com.example.cycle.parking.system.task1.cycleParkingService.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service("task1ParkingService")
public class ParkingServiceImpl implements ParkingService{
        @Autowired
        private ParkingRepository sessionRepo;

        public ParkingSession parkingSession;
        @Override
        public void checkIn(CheckInDto checkInDto) {

            Optional<ParkingSession> existingSessionOpt = sessionRepo.findByBayAndSlot(
                    checkInDto.getBay(),
                    checkInDto.getSlot()
            );



            if (existingSessionOpt.isPresent()) {
                parkingSession = existingSessionOpt.get();
                if ("OCCUPIED".equals(parkingSession.getStatus())) {

                    List<BaySlotDto> freeSlots = sessionRepo.findFreeSlots();
                    throw new BayAndSlotAlreadyException(
                            "This Bay and Slot has been registered already! ",
                            "Will be freed at " + LocalDateTime.now()
                                    .plusHours(4)
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            freeSlots
                    );
                }
            } else {
                parkingSession = CheckInMapper.maptoCustomer(checkInDto, new ParkingSession());
            }

            // Update check-in time & status
            parkingSession.setCheckInTime(LocalDateTime.now());
            parkingSession.setStatus("OCCUPIED");
            parkingSession.setVehicleNumber(checkInDto.getVehicleNumber());

            sessionRepo.save(parkingSession);
        }

        @Override
        public void checkOut(String vehicleNumber){
            Optional<ParkingSession> exitEntry = sessionRepo.findByVehicleNumber(vehicleNumber);
            if(exitEntry.isPresent()){
                parkingSession.setCheckOutTime(LocalDateTime.now());
                parkingSession.setStatus("FREE");
                parkingSession.setVehicleNumber("");
                sessionRepo.save(parkingSession);
            }
            else{
                throw new VehicleNumberNotFoundException("Vehicle Number not found! Kindly check the try again");
            }

        }
}
