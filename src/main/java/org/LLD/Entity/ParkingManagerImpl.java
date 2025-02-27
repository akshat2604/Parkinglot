package org.LLD.Entity;

import org.LLD.model.Ticket;
import org.LLD.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingManagerImpl implements ParkingManager {

    ConcurrentHashMap<Integer, Ticket> ticketHashMap = new ConcurrentHashMap<>();
    ReentrantLock reentrantLock = new ReentrantLock();
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
    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot parkingSlot = null;
        reentrantLock.lock();
        for(ParkingFloor parkingFloor : parkingFloorList){
            parkingSlot = parkingFloor.getAvailableSlots(vehicle.getVehicleType());
            if(parkingSlot == null){
                return null;
            }
        }
        parkingSlot.occupySlot();
        reentrantLock.unlock();
        Ticket ticket = new Ticket(vehicle,parkingSlot);
        ticketHashMap.put(vehicle.getVehicleId(), ticket);
        return ticket;

    }

    @Override
    public Ticket unparkVehicle(Vehicle vehicle) {
        Ticket ticket = ticketHashMap.get(vehicle.getVehicleId());
        reentrantLock.lock();
        ticket.getParkingSlot().releaseSlot();
        reentrantLock.unlock();
        return ticket;


    }


}
