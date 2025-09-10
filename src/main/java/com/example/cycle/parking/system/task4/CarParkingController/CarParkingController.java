package com.example.cycle.parking.system.task4.CarParkingController;

import com.example.cycle.parking.system.Dto.ParkingSessionCarDto;
import com.example.cycle.parking.system.task4.CarParkingEntity.BayCar;
import com.example.cycle.parking.system.task4.CarParkingEntity.SlotCar;
import com.example.cycle.parking.system.task4.CarParkingService.ParkingServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking/car")
public class CarParkingController {

    @Autowired
    private ParkingServiceCar parkingService;

    @PostMapping("/checkin")
    public ParkingSessionCarDto checkIn(@RequestParam Long slotId, @RequestParam String vehicleNumber,
                                        @RequestParam String userQr) {
        return parkingService.checkIn(slotId, vehicleNumber, userQr);
    }

    @PostMapping("/checkout")
    public ParkingSessionCarDto checkOut(@RequestParam Long sessionId) {
        return parkingService.checkOut(sessionId);
    }

    @GetMapping("/bays")
    public List<BayCar> getBays(@RequestParam int floor) {
        return parkingService.getBaysByFloor(floor);
    }

    @GetMapping("/freeslots")
    public List<SlotCar> getFreeSlots() {
        return parkingService.getFreeSlots();
    }
}