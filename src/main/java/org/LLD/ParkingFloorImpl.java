package org.LLD;

import lombok.Data;
import org.LLD.enums.VehicleType;
import org.LLD.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ParkingFloorImpl implements ParkingFloor {
    Integer floor;
    List<ParkingSlot> parkingSlotList = new ArrayList<>();

    ParkingFloorImpl(int floor, int numberOfCarSlots, int numberOfBikeSlots){
        for(int i=0;i<numberOfCarSlots;i++){
            parkingSlotList.add(new ParkingSlot(floor,i,false,true,VehicleType.CAR));
        }
        for(int i=0;i<numberOfBikeSlots;i++){
            parkingSlotList.add(new ParkingSlot(floor,i,false,true,VehicleType.BIKE));
        }
    }
    @Override
    public ParkingSlot getAvailableSlots(VehicleType vehicleType) {
        Optional<ParkingSlot> optionalParkingSlot = parkingSlotList.stream().filter(parkingSlot -> parkingSlot.isAvailable
                && parkingSlot.vehicleType == vehicleType
                && !parkingSlot.isOccupied
        ).findFirst();
        return optionalParkingSlot.orElse(null);
    }

    @Override
    public Integer getFloorNo() {
        return floor;
    }

    @Override
    public Boolean cleanup() {
        List<ParkingSlot> filteredparkingSlotList = parkingSlotList.stream().filter(parkingSlot -> parkingSlot.isOccupied).toList();
        return filteredparkingSlotList.isEmpty();
    }

    @Override
    public void addParkingSpace(ParkingSlot parkingSlot) {
        parkingSlotList.add(parkingSlot);
    }

    @Override
    public Boolean removeParkingSpace(ParkingSlot parkingSlot) {
        if(parkingSlotList.contains(parkingSlot) && !parkingSlot.isOccupied){
            parkingSlotList.remove(parkingSlot);
            return true;
        }
        return false;
    }
}
