package com.example.cycle.parking.system.task3;

import com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand;
import com.example.cycle.parking.system.Dto.ParkingSessionDto;
import com.example.cycle.parking.system.Exceptions.BayAndSlotAlreadyFloorException;
import com.example.cycle.parking.system.Exceptions.SlotAlreadyOccupiedException;
import com.example.cycle.parking.system.Mapper.ParkingMapper;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;
import com.example.cycle.parking.system.task3.FloorExtendRepository.BayRepository;
import com.example.cycle.parking.system.task3.FloorExtendRepository.ParkingSessionRepository;
import com.example.cycle.parking.system.task3.FloorExtendRepository.SlotRepository;
import com.example.cycle.parking.system.task3.FloorExtendService.Impl.ParkingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {

    @Mock
    ParkingSessionRepository sessionRepository;

    @Mock
    SlotRepository slotRepository;

    @Mock
    BayRepository bayRepository;

    @InjectMocks
    ParkingServiceImpl service;

    @Test
    void testCheckIn_FreeSlot_Success() {
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setStatus("FREE");

        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        ParkingSessionFloor session = new ParkingSessionFloor();
        session.setSlot(slot);
        session.setVehicleNumber("ABC123");
        session.setUserQr("QR123");
        session.setCheckInTime(LocalDateTime.now());
        session.setStatus("CHECKED_IN");

        when(sessionRepository.save(any(ParkingSessionFloor.class))).thenReturn(session);

        ParkingSessionDto expectedDto = new ParkingSessionDto();
        expectedDto.setVehicleNumber("ABC123");

        try (MockedStatic<ParkingMapper> mapperMock = mockStatic(ParkingMapper.class)) {
            mapperMock.when(() -> ParkingMapper.toDto(session)).thenReturn(expectedDto);

            ParkingSessionDto dto = service.checkIn(1L, "ABC123", "QR123");

            assertNotNull(dto);
            assertEquals("ABC123", dto.getVehicleNumber());
            verify(slotRepository, times(1)).save(slot);
            verify(sessionRepository, times(1)).save(any(ParkingSessionFloor.class));
        }
    }

    @Test
    void testCheckIn_OccupiedSlot_ThrowsException() {
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setStatus("OCCUPIED");

        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));
        when(sessionRepository.findFreeSlots()).thenReturn(Collections.emptyList());

        BayAndSlotAlreadyFloorException ex = assertThrows(BayAndSlotAlreadyFloorException.class,
                () -> service.checkIn(1L, "ABC123", "QR123"));
        assertTrue(ex.getMessage().contains("registered already"));
    }

    @Test
    void testCheckOut_Success() {
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setStatus("OCCUPIED");

        ParkingSessionFloor session = new ParkingSessionFloor();
        session.setId(10L);
        session.setSlot(slot);
        session.setStatus("CHECKED_IN");

        when(sessionRepository.findById(10L)).thenReturn(Optional.of(session));
        when(sessionRepository.save(any(ParkingSessionFloor.class))).thenReturn(session);

        ParkingSessionDto expectedDto = new ParkingSessionDto();
        expectedDto.setVehicleNumber("ABC123");

        try (MockedStatic<ParkingMapper> mapperMock = mockStatic(ParkingMapper.class)) {
            mapperMock.when(() -> ParkingMapper.toDto(session)).thenReturn(expectedDto);

            ParkingSessionDto dto = service.checkOut(10L);

            assertNotNull(dto);
            verify(slotRepository, times(1)).save(slot);
            verify(sessionRepository, times(1)).save(session);
        }
    }

    @Test
    void testCheckOut_SessionNotFound_ThrowsException() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(SlotAlreadyOccupiedException.class, () -> service.checkOut(99L));
    }

    @Test
    void testGetBaysByFloor_ReturnsList() {
        Bay bay1 = new Bay();
        bay1.setFloor(2);
        Bay bay2 = new Bay();
        bay2.setFloor(2);

        when(bayRepository.findByFloor(2)).thenReturn(Arrays.asList(bay1, bay2));

        List<Bay> bays = service.getBaysByFloor(2);

        assertEquals(2, bays.size());
        verify(bayRepository, times(1)).findByFloor(2);
    }

    @Test
    void testGetFreeSlots_ReturnsList() {
        Slot slot1 = new Slot();
        slot1.setStatus("FREE");
        Slot slot2 = new Slot();
        slot2.setStatus("FREE");

        when(slotRepository.findByStatus("FREE")).thenReturn(Arrays.asList(slot1, slot2));

        List<Slot> slots = service.getFreeSlots();

        assertEquals(2, slots.size());
        assertTrue(slots.stream().allMatch(s -> "FREE".equals(s.getStatus())));
        verify(slotRepository, times(1)).findByStatus("FREE");
    }
}