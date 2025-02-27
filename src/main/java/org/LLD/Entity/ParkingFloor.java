package org.LLD.Entity;

import org.LLD.enums.VehicleType;

public interface ParkingFloor {
    ParkingSlot getAvailableSlots(VehicleType vehicleType);
    Integer getFloorNo();
    Boolean cleanup();
    void addParkingSpace(ParkingSlot parkingSlot);
    Boolean removeParkingSpace(ParkingSlot parkingSlot);
}
