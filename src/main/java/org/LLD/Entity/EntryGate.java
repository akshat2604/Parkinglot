package org.LLD.Entity;

import lombok.AllArgsConstructor;
import org.LLD.model.Ticket;
import org.LLD.model.Vehicle;
import org.LLD.util.EntryGatesExecutor;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
public class EntryGate {
    Integer gateId;

    private ParkingManager parkingManager;
    private EntryGatesExecutor entryGatesExecutor;

    public Ticket parkVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException {
        return (Ticket) entryGatesExecutor.park(gateId, ()->parkingManager.parkVehicle(vehicle)).get();
    }
    public Ticket unparkVehicle(Vehicle vehicle){
        return parkingManager.unparkVehicle(vehicle);
    }


}
