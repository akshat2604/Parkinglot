package org.LLD;

import org.LLD.model.Ticket;
import org.LLD.model.Vehicle;

public interface ParkingManager {
    void addFloor(int floor, int numberOfCarSlots, int numberOfBikeSlots);
    boolean removeFloor(int floor);
    Ticket parkVehicle(ParkingSlot parkingSlot, Vehicle vehicle);
    boolean unparkVehicle(Vehicle vehicle);
}
