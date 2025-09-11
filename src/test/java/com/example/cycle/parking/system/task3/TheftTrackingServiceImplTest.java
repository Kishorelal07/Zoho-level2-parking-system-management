package com.example.cycle.parking.system.task3;

import com.example.cycle.parking.system.Dto.TheftReportDto;
import com.example.cycle.parking.system.Exceptions.TheftReportNotFoundException;
import com.example.cycle.parking.system.task2.securityEntity.TheftReport;
import com.example.cycle.parking.system.task2.securityRepository.TheftReportRepository;
import com.example.cycle.parking.system.task2.securityService.impl.TheftTrackingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TheftTrackingServiceImplTest {

    @Mock
    TheftReportRepository repository;

    @InjectMocks
    TheftTrackingServiceImpl service;

    @Test
    void testReportTheft_Success() {
        TheftReportDto dto = new TheftReportDto();
        dto.setVehicleNumber("ABC123");
        dto.setBay("A");
        dto.setSlot("1");
        dto.setPoliceStation("Central");

        TheftReport savedReport = new TheftReport();
        savedReport.setVehicleNumber("ABC123");
        savedReport.setStatus("REPORTED");

        when(repository.save(any(TheftReport.class))).thenReturn(savedReport);

        TheftReport result = service.reportTheft(dto);

        assertNotNull(result);
        assertEquals("ABC123", result.getVehicleNumber());
        assertEquals("REPORTED", result.getStatus());
        verify(repository, times(1)).save(any(TheftReport.class));
    }

    @Test
    void testGetAllOpenReports_ReturnsList() {
        TheftReport report1 = new TheftReport();
        report1.setStatus("REPORTED");
        TheftReport report2 = new TheftReport();
        report2.setStatus("REPORTED");

        when(repository.findByStatus("REPORTED")).thenReturn(Arrays.asList(report1, report2));

        List<TheftReport> reports = service.getAllOpenReports();

        assertEquals(2, reports.size());
        assertTrue(reports.stream().allMatch(r -> "REPORTED".equals(r.getStatus())));
        verify(repository, times(1)).findByStatus("REPORTED");
    }

    @Test
    void testUpdateReportStatus_Success() {
        TheftReport report = new TheftReport();
        report.setId(1L);
        report.setStatus("REPORTED");

        when(repository.findById(1L)).thenReturn(Optional.of(report));
        when(repository.save(any(TheftReport.class))).thenReturn(report);

        TheftReport updated = service.updateReportStatus(1L, "CLOSED");

        assertEquals("CLOSED", updated.getStatus());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(report);
    }

    @Test
    void testUpdateReportStatus_NotFound_ThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TheftReportNotFoundException.class, () -> service.updateReportStatus(99L, "CLOSED"));
        verify(repository, times(1)).findById(99L);
        verify(repository, never()).save(any(TheftReport.class));
    }
}