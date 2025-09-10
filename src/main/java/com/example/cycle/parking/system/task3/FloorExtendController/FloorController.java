package com.example.cycle.parking.system.task3.FloorExtendController;

import com.example.cycle.parking.system.Dto.ParkingSessionDto;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Bay;
import com.example.cycle.parking.system.task3.FloorExtendEntity.ParkingSessionFloor;
import com.example.cycle.parking.system.task3.FloorExtendEntity.Slot;
import com.example.cycle.parking.system.task3.FloorExtendService.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking/floor")
public class FloorController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/checkin")
    public ParkingSessionDto checkIn(@RequestParam Long slotId, @RequestParam String vehicleNumber,
                                     @RequestParam String userQr) {
        return parkingService.checkIn(slotId, vehicleNumber, userQr);
    }

    @PostMapping("/checkout")
    public ParkingSessionDto checkOut(@RequestParam Long sessionId) {
        return parkingService.checkOut(sessionId);
    }
}
