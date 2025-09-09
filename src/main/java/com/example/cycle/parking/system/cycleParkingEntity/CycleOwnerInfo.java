package com.example.cycle.parking.system.cycleParkingEntity;

import jakarta.persistence.*;

@Entity
public class CycleOwnerInfo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;
    @Column(name = "vehicleType")
    private String vehicleType;
    @Column(name = "vehicleNumber")
    private String vehicleNumber;
    @Column(name = "mobile_number")
    private String mobile_number;

}
