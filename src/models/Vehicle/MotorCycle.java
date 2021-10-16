package models.Vehicle;

import enumration.VehicleType;

public class MotorCycle extends Vehicle {
    public MotorCycle(VehicleType typeOfVehicle, String model, String color, String number) {
        super(VehicleType.MOTORCYCLE, model, color, number);
    }
}
