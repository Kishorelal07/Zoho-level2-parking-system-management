package com.example.cycle.parking.system.task1.cycleParkingRepository;

import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    Optional<ParkingSession> findByBayAndSlot(String bay, String slot);


    @Query("SELECT new com.example.cycle.parking.system.Dto.BaySlotDto(p.bay, p.slot) " +
            "FROM ParkingSession p WHERE p.status = 'FREE'")
    List<BaySlotDto> findFreeSlots();

    Optional<ParkingSession> findByVehicleNumber(String vehicleNumber);


}
