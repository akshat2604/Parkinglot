package org.LLD.model;

import lombok.Data;
import org.LLD.ParkingFloor;
import org.LLD.ParkingSlot;

import java.util.Date;
import java.util.UUID;

@Data
public class Ticket {
    Integer ticketId;
    Vehicle vehicle;
    ParkingSlot parkingSlot;
    Date entryTime;
    Date exitTime;

    public Ticket(Vehicle vehicle, ParkingSlot parkingSlot) {
        this.ticketId = Math.abs(UUID.randomUUID().hashCode());
        this.entryTime = new Date();
        this.vehicle = vehicle;
        this.parkingSlot = parkingSlot;

    }
}
