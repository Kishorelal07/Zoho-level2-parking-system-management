package com.example.cycle.parking.system.task4.CarParkingRepository;

import com.example.cycle.parking.system.Dto.FreeSlotDtoCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotCarRepository extends JpaRepository<SlotCar, Long> {
    List<SlotCar> findByStatus(String status);

    @Query("SELECT new com.example.cycle.parking.system.Dto.FreeSlotDtoCar(b.id, s.slotNumber, b.floor) " +
            "FROM SlotCar s JOIN s.bay b " +
            "WHERE s.status = 'FREE'")
    List<FreeSlotDtoCar> findFreeSlotDetails();
}
