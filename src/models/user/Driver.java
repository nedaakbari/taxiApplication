package models.user;

import enumration.Gender;
import enumration.DriverStatus;
import models.Vehicle.Vehicle;

import java.sql.Date;

public class Driver extends User implements Comparable<Driver> {

    private Vehicle vehicle;
    private DriverStatus tripOfDrivar;
    private double distance;

    public Driver() {
    }

    public Driver(int id, double distance) {
        super(id);
        this.distance = distance;
    }
    public Driver(int id, Location location) {
        super(id, location);
    }
    public Driver(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId, Vehicle vehicle, Location location) {
        super(name, family, birthDate, gender, phoneNumber, nationalId, location);
        this.vehicle = vehicle;
        this.tripOfDrivar = DriverStatus.WAITING;
    }

    public Driver(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId, Vehicle vehicle) {
        super(name, family, birthDate, gender, phoneNumber, nationalId);
        this.vehicle = vehicle;
        this.tripOfDrivar = DriverStatus.WAITING;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public DriverStatus getTripOfDrivar() {
        return tripOfDrivar;
    }

    public void setTripOfDrivar(DriverStatus tripOfDrivar) {
        this.tripOfDrivar = tripOfDrivar;
    }



    @Override
    public int compareTo(Driver other) {
        if (this.distance > other.distance)
            return 1;
        else if (this.distance < other.distance)
            return -1;
        else
            return 0;
    }

}