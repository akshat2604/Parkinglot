package org.LLD;

import org.LLD.enums.VehicleType;
import org.LLD.model.Vehicle;

public interface ParkingFloor {
    ParkingSlot getAvailableSlots(VehicleType vehicleType);
    Integer getFloorNo();
    Boolean cleanup();
    void addParkingSpace(ParkingSlot parkingSlot);
    Boolean removeParkingSpace(ParkingSlot parkingSlot);
}
