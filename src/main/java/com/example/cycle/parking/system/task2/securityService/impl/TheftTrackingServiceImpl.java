package com.example.cycle.parking.system.task2.securityService.impl;

import com.example.cycle.parking.system.Dto.TheftReportDto;
import com.example.cycle.parking.system.Exceptions.TheftReportNotFoundException;
import com.example.cycle.parking.system.task2.securityEntity.TheftReport;
import com.example.cycle.parking.system.task2.securityRepository.TheftReportRepository;
import com.example.cycle.parking.system.task2.securityService.TheftTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TheftTrackingServiceImpl implements TheftTrackingService {
    @Autowired
    private TheftReportRepository repository;

    @Override
    public TheftReport reportTheft(TheftReportDto dto) {
        TheftReport report = new TheftReport();
        report.setVehicleNumber(dto.getVehicleNumber());
        report.setBay(dto.getBay());
        report.setSlot(dto.getSlot());
        report.setPoliceStation(dto.getPoliceStation());
        report.setReportedAt(LocalDateTime.now());
        report.setStatus("REPORTED");
        // Optional: generate report number here if needed
        return repository.save(report);
    }

    @Override
    public List<TheftReport> getAllOpenReports() {
        return repository.findByStatus("REPORTED");
    }

    @Override
    public TheftReport updateReportStatus(Long id, String status) {
        TheftReport report = repository.findById(id)
                .orElseThrow(() -> new TheftReportNotFoundException("Theft Report Not Found"));
        report.setStatus(status);
        return repository.save(report);
    }
}
