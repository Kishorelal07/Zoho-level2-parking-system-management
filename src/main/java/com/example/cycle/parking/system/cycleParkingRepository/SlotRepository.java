package com.example.cycle.parking.system.cycleParkingRepository;

import com.example.cycle.parking.system.cycleParkingEntity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByStatus(Slot.Status status);
}