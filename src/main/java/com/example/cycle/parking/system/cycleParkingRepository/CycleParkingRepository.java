package com.example.cycle.parking.system.cycleParkingRepository;

import com.example.cycle.parking.system.cycleParkingEntity.CycleOwnerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleParkingRepository extends JpaRepository<CycleOwnerInfo, Long> {
}
