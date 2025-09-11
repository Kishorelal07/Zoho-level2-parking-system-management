package com.example.cycle.parking.system.task4;

import com.example.cycle.parking.system.Dto.ParkingSessionCarDto;
import com.example.cycle.parking.system.Mapper.ParkingMapperCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.BayCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.ParkingSessionCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;
import com.example.cycle.parking.system.task4.CarParkingRepository.BayCarRepository;
import com.example.cycle.parking.system.task4.CarParkingRepository.ParkingSessionCarRepository;
import com.example.cycle.parking.system.task4.CarParkingRepository.SlotCarRepository;
import com.example.cycle.parking.system.task4.CarParkingService.Impl.ParkingServiceCarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

@ExtendWith(MockitoExtension.class)
class ParkingServiceCarImplTest {

    @Mock
    ParkingSessionCarRepository sessionRepository;

    @Mock
    SlotCarRepository slotRepository;

    @Mock
    BayCarRepository bayRepository;

    @InjectMocks
    ParkingServiceCarImpl service;

    @Test
    void testCheckIn_FreeSlot_Success() {
        SlotCar slot = new SlotCar();
        slot.setId(1L);
        slot.setStatus("FREE");

        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        ParkingSessionCar session = new ParkingSessionCar();
        session.setId(100L); // Make sure id is set if used in toDto()
        session.setSlot(slot);
        session.setVehicleNumber("CAR123");
        session.setUserQr("QR123");
        session.setCheckInTime(LocalDateTime.now());
        session.setStatus("CHECKED_IN");

        when(sessionRepository.save(any(ParkingSessionCar.class))).thenReturn(session);

        ParkingSessionCarDto expectedDto = new ParkingSessionCarDto();
        expectedDto.setVehicleNumber("CAR123");

        try (MockedStatic<ParkingMapperCar> mapperMock = mockStatic(ParkingMapperCar.class)) {
            mapperMock.when(() -> ParkingMapperCar.toDto(session)).thenReturn(expectedDto);

            ParkingSessionCarDto dto = service.checkIn(1L, "CAR123", "QR123");

            assertNotNull(dto);
            assertEquals("CAR123", dto.getVehicleNumber());
            verify(slotRepository, times(1)).save(slot);
            verify(sessionRepository, times(1)).save(any(ParkingSessionCar.class));
        }
    }

    @Test
    void testCheckIn_OccupiedSlot_ThrowsException() {
        SlotCar slot = new SlotCar();
        slot.setId(1L);
        slot.setStatus("OCCUPIED");

        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        assertThrows(RuntimeException.class, () -> service.checkIn(1L, "CAR123", "QR123"));
    }

    @Test
    void testCheckOut_Success() {
        SlotCar slot = new SlotCar();
        slot.setId(1L);
        slot.setStatus("OCCUPIED");

        ParkingSessionCar session = new ParkingSessionCar();
        session.setId(10L);
        session.setSlot(slot);
        session.setStatus("CHECKED_IN");

        when(sessionRepository.findById(10L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(any(ParkingSessionCar.class))).thenReturn(session);

        ParkingSessionCarDto expectedDto = new ParkingSessionCarDto();
        expectedDto.setVehicleNumber("CAR123");

        try (MockedStatic<ParkingMapperCar> mapperMock = mockStatic(ParkingMapperCar.class)) {
            mapperMock.when(() -> ParkingMapperCar.toDto(session)).thenReturn(expectedDto);

            ParkingSessionCarDto dto = service.checkOut(10L);

            assertNotNull(dto);
            verify(slotRepository, times(1)).save(slot);
            verify(sessionRepository, times(1)).save(session);
        }
    }

    @Test
    void testCheckOut_SessionNotFound_ThrowsException() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.checkOut(99L));
    }

    @Test
    void testGetBaysByFloor_ReturnsList() {
        BayCar bay1 = new BayCar();
        bay1.setFloor(2);
        BayCar bay2 = new BayCar();
        bay2.setFloor(2);

        when(bayRepository.findByFloor(2)).thenReturn(Arrays.asList(bay1, bay2));

        List<BayCar> bays = service.getBaysByFloor(2);

        assertEquals(2, bays.size());
        verify(bayRepository, times(1)).findByFloor(2);
    }

    @Test
    void testGetFreeSlots_ReturnsList() {
        SlotCar slot1 = new SlotCar();
        slot1.setStatus("FREE");
        SlotCar slot2 = new SlotCar();
        slot2.setStatus("FREE");

        when(slotRepository.findByStatus("FREE")).thenReturn(Arrays.asList(slot1, slot2));

        List<SlotCar> slots = service.getFreeSlots();

        assertEquals(2, slots.size());
        assertTrue(slots.stream().allMatch(s -> "FREE".equals(s.getStatus())));
        verify(slotRepository, times(1)).findByStatus("FREE");
    }
}