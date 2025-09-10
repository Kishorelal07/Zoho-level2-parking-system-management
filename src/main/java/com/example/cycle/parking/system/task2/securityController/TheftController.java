package com.example.cycle.parking.system.task2.securityController;

import com.example.cycle.parking.system.Dto.TheftReportDto;
import com.example.cycle.parking.system.task2.securityEntity.TheftReport;
import com.example.cycle.parking.system.task2.securityService.TheftTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theft")
public class TheftController {

    @Autowired
    private TheftTrackingService service;

    @PostMapping("/report")
    public ResponseEntity<TheftReport> reportTheft(@RequestBody TheftReportDto dto) {
        TheftReport report = service.reportTheft(dto);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/open")
    public ResponseEntity<List<TheftReport>> getOpenReports() {
        List<TheftReport> reports = service.getAllOpenReports();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TheftReport> updateStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        TheftReport updated = service.updateReportStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
