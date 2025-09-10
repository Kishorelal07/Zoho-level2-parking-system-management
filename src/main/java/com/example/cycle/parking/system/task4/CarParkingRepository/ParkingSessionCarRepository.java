package com.example.cycle.parking.system.task4.CarParkingRepository;

import com.example.cycle.parking.system.Dto.BaySlotDtoCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.ParkingSessionCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSessionCarRepository extends JpaRepository<ParkingSessionCar, Long> {

    List<ParkingSessionCar> findByStatus(String status);

    @Query("SELECT new com.example.cycle.parking.system.Dto.BaySlotDtoCar(b.id, s.slotNumber, b.floor) " +
            "FROM SlotCar s JOIN s.bay b " +
            "WHERE s.status = 'FREE'")
    List<BaySlotDtoCar> findFreeSlots();
}
