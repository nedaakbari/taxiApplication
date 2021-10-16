import Exceptions.*;
import enumration.*;
import models.Vehicle.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import models.user.Driver;
import models.user.Passenger;

public class Main {

    static TaxiApplication application = new TaxiApplication();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        boolean mainLoop = true;

        do {
            try {
                showMenu:
                firstPageMenue();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("\nHow many drivers do you want to add?");
                        int countOfDrivers = scanner.nextInt();
                        for (int i = 1; i <= countOfDrivers; i++) {
                            System.out.println("ENTER INFORMATION OF DEIVER " + i);
                            addDriver();
                        }
                        System.out.println("Your information was successfully registered.");
                        mainLoop = true;
                        break;
                    case 2:
                        System.out.println("How many Passenger do you want to add?");
                        int countOfPassenger = scanner.nextInt();
                        for (int i = 1; i <= countOfPassenger; i++) {
                            System.out.println("ENTER INFORMATION OF PASSENGER " + i);
                            addPassenger();
                        }
                        mainLoop = true;
                        break;
                    case 3:
                        driverLoginOrRegister();
                        mainLoop = true;
                        break;
                    case 4:
                        passengerLoginOrRegister();
                        mainLoop = true;
                        break;
                    case 5:
                        mainLoop = true;
                        break;
                    case 6:
                        application.printAllDriver();
                        mainLoop = true;
                        break;
                    case 7:
                        application.printAllPassenger();
                        mainLoop = true;
                        break;
                    case 8:
                        System.out.println("have a nice day");
                        mainLoop = false;
                        break;
                    default:
                        System.out.println("input not valid");

                }//swich
            } catch (Exception e) {
                e.getMessage();
            }
        } while (mainLoop);
    }//main

    public static void firstPageMenue() {
        System.out.print("\n********** Menue **********" +
                "\n1.Add a group of drivers\n2.Add a group of passengers\n3.Driver signup or login" +
                "\n4.Passenger signup or login\n5.Show ongoing travels\n6.Show a list of drivers" +
                "\n7.Show a list of passengers\n8.exit\nchoose a number:");
    }


    public static void addDriver() throws SQLException, PlaqueException, InvalidNationalCode, InvalidPhoneException, InvalidFullNameException, DriverDuplicate {
        System.out.println("Enter name,family,phoneNumber,nationalId,birthDate of person  like 1999-02-04,gender ....> MALE or FEMALE ");
        String information = scanner.nextLine();
        String[] info = information.split(",", 5);
        String name = info[0];
        String family = info[1];
        String phoneNumber = info[2];
        String nationalCode = info[3];
        Date birthDate = Date.valueOf(info[4]);
        Gender gender = Gender.valueOf(info[5]);
        System.out.println("\n Enter information of  vehicle");
        System.out.println("kind of vehicle?   1.car  2.motorcycle   3.van   ");
        int choice = scanner.nextInt();
        VehicleType kindOfVehicle;
        switch (choice) {
            case 1:
                kindOfVehicle = VehicleType.CAR;
                break;
            case 2:
                kindOfVehicle = VehicleType.MOTORCYCLE;
                break;
            case 3:
                kindOfVehicle = VehicleType.VAN;
                break;
            default:
                kindOfVehicle = VehicleType.CAR;
                break;
        }
        System.out.println("model,collor,plaque ?");
        String model = scanner.next();
        String color = scanner.next();
        String plaqu = scanner.next();
        System.out.println("Enter Location by cama   Like 9000, 2000");
        Location driverOrigin = getLocation();
        Vehicle vehicle = application.addVehicle(kindOfVehicle, model, color, plaqu);
        Vehicle vehicleID = application.findVehicleID(plaqu);
        application.addDriver(name, family, birthDate, gender, phoneNumber, nationalCode, vehicleID, driverOrigin);
    }

    public static void addPassenger() throws SQLException, InvalidNationalCode, InvalidPhoneException, UserException, InvalidFullNameException {
        System.out.println("Enter name,family,phoneNumber,nationalId,birthDate of person  like 1999-02-04,gender ....> MALE or FEMALE,balance ");
        String information = scanner.nextLine();
        String[] info = information.split(",", 6);
        String name = info[0];
        String family = info[1];
        String phoneNumber = info[2];
        String nationalCode = info[3];
        Date birthDate = Date.valueOf(info[4]);
        Gender gender = Gender.valueOf(info[5]);
        double balance = Double.parseDouble(info[6]);
        application.addPassenger(name, family, birthDate, gender, phoneNumber, nationalCode, balance);
    }


    public static void driverLoginOrRegister() throws SQLException, InvalidNationalCode, PlaqueException, InvalidPhoneException, InvalidFullNameException, DriverDuplicate {
        Driver foundDriver;
        outer:
        while (true) {
            System.out.println("Username(nationalCode)");

            String case3userName = scanner.next();
            foundDriver = application.driverDao.findUserName(case3userName);
            if (foundDriver == null) {
                inner:
                System.out.println("1.Register \n2.Exit");
                int input = scanner.nextInt();
                while (input != 2) {
                    switch (input) {
                        case 1:
                            addDriver();
                            break;
                        case 2:
                            break outer;

                    }//switch
                }
            }//if
            else {
                if (foundDriver.getTripOfDrivar() == DriverStatus.WAITING) {
                    System.out.println("you are waiting for find a trip\n1.EXIT");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            break mainLoop;
                        default:
                            System.out.println("input unavalable");
                            break;
                    }
                } else {
                    System.out.println(foundDriver.getTripOfDrivar());//tripstatus
                    System.out.println("1.Confirm cash receipt\n" +
                            "2.Travel finished\n" +
                            "3.Exit");
                }//else


            }//else


        }//while
    }//driverLoginOrRegister()


    public static void passengerLoginOrRegister() throws SQLException, InvalidNationalCode, InvalidPhoneException, UserException, InvalidFullNameException {
        Passenger foundPassenger;
        outer:
        while (true) {
            System.out.println("Username(nationalCode)");
            String case3userName = scanner.next();
            foundPassenger = application.passengerDao.findPassengerByUsername(case3userName);
            if (foundPassenger == null) {
                inner:
                System.out.println("1.Register \n2.Exit");
                int input = scanner.nextInt();
                while (input != 2) {
                    switch (input) {
                        case 1:
                            addPassenger();
                            break;
                        case 2:
                            break outer;

                    }//switch
                }
            }//if
            else if (foundPassenger.getStatus() == PassengerStatus.ABSENCE) {
                System.out.println(foundPassenger);
                boolean ifloop = true;
                while (ifloop) {
                    System.out.println("1.Travel request (pay by cash)\n" +
                            "2.Travel request (pay by account balance)\n" +
                            "3.Increase account balance\n" +
                            "4.Exit");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter the origin and destination of your travel:");
                            Location origin1 = getLocation();
                            Location destination1 = getLocation();
                            foundPassenger.setStatus(PassengerStatus.ONTRIP);
                            int costOfTrip1 = (int) (Trip.costEveryMeters * application.calculateDistance(origin1, destination1));
                            Driver closestDriver = application.findClosestDriver(origin1);
                            closestDriver.setTripOfDrivar(DriverStatus.ONTRIP);
                            closestDriver.setLocation(destination1);
                            application.driverDao.updateDriverLocation(closestDriver.getId(), destination1);
                            application.addTrip(foundPassenger, closestDriver, origin1, destination1, costOfTrip1, PayBy.BYCASH);
                            application.driverDao.updateDriverLocation(closestDriver.getId(), destination1);
                            break;

                        case 2:
                            System.out.println("Enter the origin and destination of your travel:");
                            Location origin2 = getLocation();
                            Location destination2 = getLocation();

                            int costOfTrip2 = (int) (Trip.costEveryMeters * application.calculateDistance(origin2, destination2));
                            if (costOfTrip2 < foundPassenger.getBalance()) {
                                foundPassenger.setStatus(PassengerStatus.ONTRIP);
                                Driver closestDriver2 = application.findClosestDriver(origin2);
                                closestDriver2.setTripOfDrivar(DriverStatus.ONTRIP);
                                closestDriver2.setLocation(destination2);
                                application.driverDao.updateDriverLocation(closestDriver2.getId(), destination2);
                                application.addTrip(foundPassenger, closestDriver2, origin2, destination2, costOfTrip2, PayBy.BYACCCOUNTBALANCE);
                                double amount = -(costOfTrip2);
                                application.passengerDao.updatePassengerBalance(foundPassenger.getNationalId(), amount);

                                ifloop = false;
                                break;
                            } else {
                                System.out.println("you dont have enough money \n1.Exit");//
                                int choice1 = scanner.nextInt();
                                ifloop = true;
                                break;
                            }
                        case 3:
                            System.out.println("Enter amount in RIAL");
                            double amount = scanner.nextDouble();
                            application.passengerDao.updatePassengerBalance(foundPassenger.getNationalId(), amount);
                            break;
                        case 4:
                            break outer;
                    }
                }//switch

            }//if
            else {
                //passenger is on trip
            }

        }//while
    }

    private static Location getLocation() {
        String inputLocation = scanner.nextLine();
        String[] locationIn2array = inputLocation.split(",", 2);
        int xLocation = Integer.parseInt(locationIn2array[0]);
        int yLocation = Integer.parseInt(locationIn2array[1]);
        return new Location(xLocation, yLocation);
    }


}//Main