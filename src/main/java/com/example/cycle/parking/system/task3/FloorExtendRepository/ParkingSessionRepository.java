package com.example.cycle.parking.system.task3.FloorExtendRepository;


import com.example.cycle.parking.system.Dto.BaySlotDto;
import com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSessionFloor, Long> {
    List<ParkingSessionFloor> findByStatus(String status);

    @Query("SELECT new com.example.cycle.parking.system.Dto.BaySlotDtoFloorExpand(b.id, s.slotNumber, b.floor) " +
            "FROM Slot s JOIN s.bay b " +
            "WHERE s.status = 'FREE'")
    List<BaySlotDtoFloorExpand> findFreeSlots();

}