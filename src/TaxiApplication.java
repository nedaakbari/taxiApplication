import Exceptions.*;
import dataAccess.DriverDataAccess;
import dataAccess.PassengerDataAccess;
import dataAccess.VehicleDataAccess;
import enumration.Gender;
import enumration.PayBy;
import enumration.VehicleType;
import models.Location;
import models.Trip;
import models.Vehicle.Vehicle;
import models.user.Driver;
import models.user.Passenger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiApplication {
    VehicleDataAccess vehicleDao = new VehicleDataAccess();
    DriverDataAccess driverDao = new DriverDataAccess();
    PassengerDataAccess passengerDao = new PassengerDataAccess();
    TripDataAccess tripDao = new TripDataAccess();
    List<Driver> driversList = new ArrayList<>();
    List<Passenger> passengerList = new ArrayList<>();


    public Vehicle addVehicle(VehicleType typeOfVehicle, String model, String color, String plaque) throws SQLException, PlaqueException {
        Vehicle vehicle = new Vehicle(typeOfVehicle, model, color, plaque);
        if (vehicleDao.findVehicleIdByPlaque(plaque) == null) {
            vehicleDao.save(vehicle);
            return vehicle;
        } else
            throw new PlaqueException("Duplicate Exception");
    }


    public void addDriver(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId, Vehicle vehicleID, Location location) throws InvalidFullNameException, InvalidPhoneException, InvalidNationalCode, DriverDuplicate, SQLException {
        Driver driver = new Driver(name, family, birthDate, gender, phoneNumber, nationalId, vehicleID, location);
        if (driverDao.findUserName(nationalId) == null) {
            if (isValidateFullName(name, family) && isValidatePhone(phoneNumber) && isValidateNationalCode(nationalId)) {
                driverDao.save(driver);
            }
        } else {
            throw new DriverDuplicate("Duplicate Diver");
        }
    }

    public Vehicle findVehicleID(String plaqu) throws SQLException {
        return vehicleDao.findVehicleIdByPlaque(plaqu);
    }

    public void addPassenger(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalCode, double balance) throws UserException, SQLException, InvalidFullNameException, InvalidPhoneException, InvalidNationalCode {
        Passenger passenger = new Passenger(name, family, birthDate, gender, phoneNumber, nationalCode, balance);
        if (passengerDao.findPassengerByUsername(nationalCode) == null) {
            if (isValidateFullName(name, family) && isValidatePhone(phoneNumber) && isValidateNationalCode(nationalCode)) {
                passengerDao.save(passenger);
            }
        } else {
            throw new UserException("DuplicateException");
        }
    }


    public boolean isValidatePhone(String phoneNumber) throws InvalidPhoneException {
        if (phoneNumber.matches("[0-9]+") && phoneNumber.trim().startsWith("09") && phoneNumber.trim().length() == 11) {
            return true;
        }
        throw new InvalidPhoneException("phoneNumber input is not Valid");
    }

    public boolean isValidateNationalCode(String nationalCode) throws InvalidNationalCode {
        int national = Integer.parseInt(nationalCode);
        if (nationalCode.trim().length() == 10 && national > 0 && nationalCode.matches("[0-9]+")) {
            return true;
        }
        throw new InvalidNationalCode("nationalCode input is not Valid");
    }

    public boolean isValidateFullName(String name, String family) throws InvalidFullNameException {
        if (name.length() != 0 && family.length() != 0 && name.matches("[a-zA-Z]+") && family.matches(" [a-zA-Z]+")) {
            return true;
        } else {
            throw new InvalidFullNameException("name or family input is not Valid");
        }
    }


    public void printAllPassenger() {
        try {
            passengerList = passengerDao.findAllPassenger();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Passenger item : passengerList)
            System.out.println(item);
    }


    public void printAllDriver() {
        try {
            driversList = driverDao.findAllDrivers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Driver item : driversList)
            System.out.println(item);
    }

    public Double calculateDistance(Location origin, Location destination) {
        double resultOrigin = (Math.pow(origin.getX(), 2) - Math.pow(destination.getX(), 2));
        double resultDestination = (Math.pow(origin.getY(), 2) - Math.pow(destination.getY(), 2));
        Double result = (Math.sqrt(resultOrigin) + Math.sqrt(resultDestination));
        return result;
    }

    public Driver findClosestDriver(Location locationPassenger) throws SQLException {
        List<Driver> findDrivers = new ArrayList<>();

        driversList = driverDao.findAllDriversLocation();
        for (Driver item : driversList) {
            Driver driver = new Driver(item.getId(), calculateDistance(item.getLocation(), locationPassenger));
            findDrivers.add(driver);
        }
        Driver closest = getClosest(findDrivers);
        return closest;

    }


    public Trip addTrip(Passenger passenger, Driver driver, Location origin, Location destination, int cost, PayBy payBy) throws SQLException {
        Trip trip = new Trip(passenger, driver, origin, destination, cost, payBy);
        tripDao.save(trip);
        return trip;
    }


    private Driver getClosest(List<Driver> driversList) {
        Driver closest = driversList.get(0);
        for (Driver item : driversList) {
            if (closest.compareTo(item) == 1) {
                closest = item;
            }
            return closest;
        }
        return null;
    }

}