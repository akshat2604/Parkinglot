package org.LLD;

import lombok.AllArgsConstructor;
import org.LLD.enums.VehicleType;

@AllArgsConstructor
public class ParkingSlot {
    Integer floor;
    Integer slotId;
    Boolean isOccupied;
    Boolean isAvailable;
    VehicleType vehicleType;

    public synchronized Boolean occupySlot(){
        if(isAvailable && !isOccupied){
            isOccupied = true;
            isAvailable = false;
            return true;
        }
        return false;
    }
    public synchronized Boolean releaseSlot(){
        if(isOccupied){
            isOccupied = false;
            return true;
        }
        return false;
    }
}
