package models.user;

import enumration.Gender;
import enumration.PassengerStatus;

import java.sql.Date;

public class Passenger extends User {
    private double balance;
    private PassengerStatus status;




    public Passenger() {
    }

    public Passenger(String userName) {
        super(userName);
    }

    public Passenger(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId, double balance) {
        super(name, family, birthDate, gender, phoneNumber, nationalId);
        this.balance = balance;
        this.status = PassengerStatus.ABSENCE;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PassengerStatus getStatus() {
        return status;
    }

    public void setStatus(PassengerStatus status) {
        this.status = status;
    }


}
