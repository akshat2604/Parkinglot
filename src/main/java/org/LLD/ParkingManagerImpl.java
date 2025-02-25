package org.LLD;

import org.LLD.model.Ticket;
import org.LLD.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingManagerImpl implements ParkingManager {

    static ConcurrentHashMap<Integer, Ticket> ticketHashMap = new ConcurrentHashMap<>();
    List<ParkingFloor> parkingFloorList = new ArrayList<>();
    @Override
    public void addFloor(int floor, int numberOfCarSlots, int numberOfBikeSlots) {
        parkingFloorList.add(new ParkingFloorImpl(floor,numberOfCarSlots,numberOfBikeSlots));
    }

    @Override
    public boolean removeFloor(int floor) {
        for (ParkingFloor parkingFloor : parkingFloorList) {
            if (parkingFloor.getFloorNo() == floor) {
                if(parkingFloor.cleanup()) {
                    parkingFloorList.remove(parkingFloor);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Ticket parkVehicle(ParkingSlot parkingSlot, Vehicle vehicle) {
        Boolean parkVehicle = parkingSlot.occupySlot();
        if(!parkVehicle){
            return null;
        }
        Ticket ticket = new Ticket(vehicle,parkingSlot);
        ticketHashMap.put(vehicle.getVehicleId(), ticket);
        return ticket;

    }

    @Override
    public boolean unparkVehicle(Vehicle vehicle) {
        return false;
    }


}
