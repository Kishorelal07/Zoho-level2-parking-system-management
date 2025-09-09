package com.example.cycle.parking.system.cycleParkingRepository;

import com.example.cycle.parking.system.cycleParkingEntity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> { }
