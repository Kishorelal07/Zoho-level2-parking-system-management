package com.example.cycle.parking.system.task1.cycleParkingController;


import Constants.GeneralConstants;
import com.example.cycle.parking.system.Dto.CheckInDto;
import com.example.cycle.parking.system.Dto.ResponseDto;
import com.example.cycle.parking.system.task1.cycleParkingService.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/checkin")
    public ResponseEntity<ResponseDto> createEntry(@RequestBody CheckInDto checkInDto) {
        parkingService.checkIn(checkInDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(GeneralConstants.Occupied_free, GeneralConstants.Bay));
    }

    @PostMapping("/checkout")
    public ResponseEntity<ResponseDto> ExitEntry(@RequestParam String vehicleNumber) {
        parkingService.checkOut(vehicleNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(GeneralConstants.checked_out, GeneralConstants.thankyou_msg));
    }
}
