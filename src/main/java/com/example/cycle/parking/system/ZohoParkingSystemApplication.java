package com.example.cycle.parking.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
public class ZohoParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZohoParkingSystemApplication.class, args);
	}
}
