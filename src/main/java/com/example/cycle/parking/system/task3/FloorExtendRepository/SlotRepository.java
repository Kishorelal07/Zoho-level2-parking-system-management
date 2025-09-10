package com.example.cycle.parking.system.task3.FloorExtendRepository;

import com.example.cycle.parking.system.task1.cycleParkingEntity.ParkingSession;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByStatus(String status);
}