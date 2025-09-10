package com.example.cycle.parking.system.task2.securityService;


import com.example.cycle.parking.system.Dto.TheftReportDto;
import com.example.cycle.parking.system.task2.securityEntity.TheftReport;

import java.util.List;

public interface TheftTrackingService {

    TheftReport reportTheft(TheftReportDto dto);

    List<TheftReport> getAllOpenReports();

    TheftReport updateReportStatus(Long id, String status);
}
