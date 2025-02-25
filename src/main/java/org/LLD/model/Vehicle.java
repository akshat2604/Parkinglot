package org.LLD.model;

import lombok.Data;
import org.LLD.enums.VehicleType;

@Data
public class Vehicle {
    Integer vehicleId;
    String vehicleNumber;
    VehicleType vehicleType;
}
