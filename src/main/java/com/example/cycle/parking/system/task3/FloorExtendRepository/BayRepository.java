package com.example.cycle.parking.system.task3.FloorExtendRepository;

import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BayRepository extends JpaRepository<Bay, Long> {
    List<Bay> findByFloor(Integer floor);
}
