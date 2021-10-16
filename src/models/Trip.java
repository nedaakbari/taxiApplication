package models;

import enumration.PayBy;
import models.user.Driver;
import models.user.Passenger;

public class Trip {
    public static final int costEveryMeters = 1000;
    private int id;
    private Driver driver;
    private Passenger passenger;
    private Location origin;
    private Location destination;
    private int cost;
    private PayBy payBy;

    public Trip(Passenger passenger,Driver driver,  Location origin, Location destination, int cost, PayBy payBy) {
        this.driver = driver;
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.payBy = payBy;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PayBy getPayBy() {
        return payBy;
    }

    public void setPayBy(PayBy payBy) {
        this.payBy = payBy;
    }


    @Override
    public String toString() {
        return "Trip{" +
                "driver=" + driver +
                ", passenger=" + passenger +
                ", origin=" + origin +
                ", destination=" + destination +
                ", cost=" + cost +
                ", payBy=" + payBy +
                '}';
    }
}
