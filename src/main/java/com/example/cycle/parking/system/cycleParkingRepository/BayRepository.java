package com.example.cycle.parking.system.cycleParkingRepository;

import com.example.cycle.parking.system.cycleParkingEntity.Bay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BayRepository extends JpaRepository<Bay, Long> { }