package com.example.cycle.parking.system.task2.securityRepository;

import com.example.cycle.parking.system.task2.securityEntity.TheftReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheftReportRepository extends JpaRepository<TheftReport, Long> {
    Optional<TheftReport> findByVehicleNumberAndStatus(String vehicleNumber, String status);
    List<TheftReport> findByStatus(String status);
}
