package com.example.cycle.parking.system.task1;

import com.example.cycle.parking.system.Dto.CheckInDto;
import com.example.cycle.parking.system.Exceptions.BayAndSlotAlreadyException;
import com.example.cycle.parking.system.Exceptions.VehicleNumberNotFoundException;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;
import com.example.cycle.parking.system.task1.cycleParkingRepository.ParkingRepository;
import com.example.cycle.parking.system.task1.cycleParkingService.impl.ParkingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {

    @Mock
    ParkingRepository sessionRepo;

    @InjectMocks
    ParkingServiceImpl parkingService;

    @Test
    void testCheckIn_FreeSlot_Success() {
        System.out.println("Running testCheckIn_FreeSlot_Success");
        CheckInDto dto = new CheckInDto();
        dto.setBay("A");
        dto.setSlot("1");
        dto.setVehicleNumber("ABC123");

        when(sessionRepo.findByBayAndSlot("A", "1")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> parkingService.checkIn(dto));
        verify(sessionRepo, times(1)).save(any(ParkingSession.class));
    }

    @Test
    void testCheckIn_OccupiedSlot_ThrowsException() {
        CheckInDto dto = new CheckInDto();
        dto.setBay("A");
        dto.setSlot("1");
        dto.setVehicleNumber("ABC123");

        ParkingSession occupiedSession = new ParkingSession();
        occupiedSession.setStatus("OCCUPIED");

        when(sessionRepo.findByBayAndSlot("A", "1")).thenReturn(Optional.of(occupiedSession));
        when(sessionRepo.findFreeSlots()).thenReturn(Collections.emptyList());

        BayAndSlotAlreadyException ex = assertThrows(BayAndSlotAlreadyException.class, () -> parkingService.checkIn(dto));
        assertTrue(ex.getMessage().contains("registered already"));
    }

    @Test
    void testCheckOut_VehicleExists_Success() {
        ParkingSession session = new ParkingSession();
        session.setStatus("OCCUPIED");
        session.setVehicleNumber("ABC123");

        when(sessionRepo.findByVehicleNumber("ABC123")).thenReturn(Optional.of(session));

        parkingService.parkingSession = session;

        assertDoesNotThrow(() -> parkingService.checkOut("ABC123"));
        verify(sessionRepo, times(1)).save(any(ParkingSession.class));
    }

    @Test
    void testCheckOut_VehicleNotFound_ThrowsException() {
        when(sessionRepo.findByVehicleNumber("XYZ999")).thenReturn(Optional.empty());

        assertThrows(VehicleNumberNotFoundException.class, () -> parkingService.checkOut("XYZ999"));
    }
}