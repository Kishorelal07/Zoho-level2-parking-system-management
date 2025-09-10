package com.example.cycle.parking.system.task4.CarParkingRepository;

import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotCarRepository extends JpaRepository<SlotCar, Long> {
    List<SlotCar> findByStatus(String status);
}
