package models.user;

import enumration.Gender;
import enumration.UserRole;


import java.sql.Date;
import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String name;
    private String family;
    private Date birthDate;
    private Gender gender;
    private String phoneNumber;
    private String nationalId;
    private UserRole userRole;
    private Location location;
    private Trip trip;

    public User(int id) {
        this.id = id;
    }

    public User(int id, Location location) {
        this.id = id;
        this.location = location;
    }


    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }


    public User(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId, Location location) {
        this.userName = nationalId;
        this.name = name;
        this.family = family;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.location = location;
    }

    public User(String name, String family, Date birthDate, Gender gender, String phoneNumber, String nationalId) {
        this.userName = nationalId;
        this.name = name;
        this.family = family;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        //this.userRole=

    }

    public Location getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nationalId, user.nationalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId);
    }


}
