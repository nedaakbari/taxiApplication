package dataAccess;

import enumration.Gender;
import models.user.Passenger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerDataAccess extends DataAccess {
    public PassengerDataAccess() {
    }

    public int save(Passenger passenger) throws SQLException {
        if (connection != null) {
            String query = "INSERT INTO passenger (`nationalCode`, `name`, `family`, `phoneNumber`, `gender`, `userName`, `birthDate`, `balance`) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, passenger.getNationalId());
            statement.setString(2, passenger.getName());
            statement.setString(3, passenger.getFamily());
            statement.setString(4, passenger.getPhoneNumber());
            statement.setString(5, Gender.getValuetoString(passenger.getGender()));
            statement.setString(6, passenger.getNationalId());
            statement.setDate(7, passenger.getBirthDate());
            statement.setDouble(8, 0.00);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new passeneger  inserted successfully!");
                return rowsInserted;
            }
        }
        return 0;
    }


    public Passenger findPassengerByUsername(String userName) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM passengers where userName='" + userName + "';");
            while (resultSet.next()) {
                Passenger findPassenger = new Passenger();
                findPassenger.setName(resultSet.getString("name"));
                findPassenger.setFamily(resultSet.getString("family"));
                findPassenger.setNationalId(resultSet.getString("nationalCode"));
                findPassenger.setPhoneNumber(resultSet.getString("phoneNumber"));
                findPassenger.setBirthDate(resultSet.getDate("birthDate"));
                findPassenger.setBalance(resultSet.getDouble("balance"));
                findPassenger.setUserName(resultSet.getString("userName"));
                return findPassenger;
            }
        }
        return null;
    }


    public int updatePassengerBalance(String username, double amount) throws SQLException {
        if (connection != null) {
            String sql = "UPDATE passenger SET balance = balance + ?  WHERE userName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, amount);
            statement.setString(2, username);
            int i = statement.executeUpdate();
            return i;
        } else {
            return 0;
        }
    }


    public List<Passenger> findAllPassenger() throws SQLException {
        if (connection != null) {
            List<Passenger> passengerList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from passenger");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                Passenger passenger = new Passenger();
                passenger.setName(resultSet.getString("name"));
                passenger.setFamily(resultSet.getString("family"));
                passenger.setBirthDate(resultSet.getDate("birthDate"));
                passenger.setGender(Gender.getValue(gender));
                passenger.setPhoneNumber(resultSet.getString("phoneNumber"));
                passenger.setNationalId(resultSet.getString("nationalId"));
                passenger.setBalance(resultSet.getDouble("balance"));
                passengerList.add(passenger);
            }
            return passengerList;

        } else {
            return Collections.emptyList();
        }
    }


}