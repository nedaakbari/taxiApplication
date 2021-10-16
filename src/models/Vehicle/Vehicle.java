package models.Vehicle;

import enumration.VehicleType;

public class Vehicle {
    private int id;
    private VehicleType typeOfVehicle;
    private String model;
    private String color;
    private String plaque;


    public Vehicle(int id) {
        this.id = id;
    }

    public Vehicle(String plaque) {
        this.plaque = plaque;
    }

    public Vehicle() {
    }

    public Vehicle(VehicleType typeOfVehicle, String model, String color, String number) {
        this.typeOfVehicle = typeOfVehicle;
        this.model = model;
        this.color = color;
        plaque = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleType getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(VehicleType typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }
}
