package org.LLD.Entity;

import lombok.AllArgsConstructor;
import org.LLD.enums.VehicleType;

@AllArgsConstructor
public class ParkingSlot {
    Integer floor;
    Integer slotId;
    Boolean isOccupied;
    Boolean isAvailable;
    VehicleType vehicleType;

    public void occupySlot(){
       isOccupied = true;
    }
    public void releaseSlot(){
        isOccupied = false;
    }
}
