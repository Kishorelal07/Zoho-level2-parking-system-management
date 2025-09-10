package com.example.cycle.parking.system.task4.CarParkingRepository;

import com.example.cycle.parking.system.task4.CarParkingEntity.BayCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BayCarRepository extends JpaRepository<BayCar, Long> {
    List<BayCar> findByFloor(Integer floor);
}