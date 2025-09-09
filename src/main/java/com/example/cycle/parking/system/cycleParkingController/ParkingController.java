package com.example.cycle.parking.system.cycleParkingController;


import com.example.cycle.parking.system.cycleParkingService.CycleParkingService;
import com.example.cycle.parking.system.dto.CustomerDto;
import com.example.cycle.parking.system.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private CycleParkingService parkingService;


}
