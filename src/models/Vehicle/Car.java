package models.Vehicle;


import enumration.VehicleType;

public class Car extends Vehicle {

    //  setTypeOfVehicle(VehicleType.CAR);

    public Car(VehicleType typeOfVehicle, String model, String color, String number) {
        super(VehicleType.CAR, model, color, number);
    }
}
