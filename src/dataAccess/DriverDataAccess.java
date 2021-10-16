
package dataAccess;


import enumration.Gender;
import enumration.VehicleType;
import models.Vehicle.Vehicle;
import models.user.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverDataAccess extends DataAccess {
    VehicleDataAccess vehicleDataAccess = new VehicleDataAccess();

    public DriverDataAccess() {
    }

    public int save(Driver driver) throws SQLException {
        if (connection != null) {
            String query = "INSERT INTO driver (`nationalCode`, `name`, `family`, `phoneNumber`, `gender`, `userName`, `birthDate`,`location`, `vehicle_id`) VALUES (?,?, ?, ?, ?,?,?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, driver.getNationalId());
            statement.setString(2, driver.getName());
            statement.setString(3, driver.getFamily());
            statement.setString(4, driver.getPhoneNumber());
            statement.setString(5, Gender.getValuetoString(driver.getGender()));
            statement.setString(6, driver.getUserName());
            statement.setDate(7, driver.getBirthDate());
            String location = driver.getLocation().getX() + "," + driver.getLocation().getY();
            statement.setString(8, location);
            statement.setInt(9, driver.getVehicle().getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new driver was inserted successfully!");
                return rowsInserted;
            }
        }
        return 0;
    }



    public Driver findUserName(String nationalId) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM driver where userNaame='" + nationalId + "';");
            while (resultSet.next()) {
                Driver findDriver = new Driver();
                findDriver.setUserName(resultSet.getString("userName"));
                findDriver.setName(resultSet.getString("name"));
                findDriver.setFamily(resultSet.getString("family"));
                findDriver.setBirthDate(resultSet.getDate("birthDate"));
                String gender = resultSet.getString("gender");
                findDriver.setGender(Gender.getValue(gender));
                findDriver.setPhoneNumber(resultSet.getString("phoneNumber"));
                findDriver.setNationalId(resultSet.getString("nationalId"));
                int vehicleId = resultSet.getInt("vehicleId");
                findDriver.setVehicle(vehicleDataAccess.getVehicleById(vehicleId));
                return findDriver;
            }
        }
        return null;
    }


    public List<Driver> findAllDrivers() throws SQLException {
        if (connection != null) {
            List<Driver> driversList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT userName,name,family,birthDate,gender,phoneNumber,nationalId,type,model,color,plaque\n" +
                    "FROM taxionline.driver\n" +
                    "inner join taxionline.vehicle\n" +
                    "on taxionline.driver.vehicle_id=taxionline.vehicle.id;");
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                String type = resultSet.getString("type");
                Vehicle vehicle = new Vehicle(VehicleType.getValue(type), resultSet.getString("model"), resultSet.getString("color"), resultSet.getString("plaque"));
                Driver driver = new Driver(resultSet.getString("name"), resultSet.getString("family"), resultSet.getDate("birthDate"), Gender.getValue(gender), resultSet.getString("phoneNumber"), resultSet.getString("nationalCode"), vehicle);
                driversList.add(driver);
            }
            return driversList;
        } else {
            return Collections.emptyList();
        }
    }

    public List<Driver> findAllDriversLocation() throws SQLException {
        if (connection != null) {
            List<Driver> driversList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,location from driver");
            while (resultSet.next()) {
                String location = resultSet.getString("location");
                String[] location1 = location.split(",");
                int x = Integer.parseInt(location1[0]);
                int y = Integer.parseInt(location1[1]);
                Location location2 = new Location(x, y);
                Driver driver = new Driver(resultSet.getInt("id"), location2);
                driversList.add(driver);
            }
            return driversList;
        } else {
            return Collections.emptyList();
        }
    }


    public int updateDriverLocation(int id, Location location) throws SQLException {
        if (connection != null) {
            String sql = "UPDATE `taxi`.`driver` SET `location` = ? WHERE (`id` = ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            String location1 = location.getX() + "," + location.getY();
            statement.setString(1, location1);
            statement.setInt(2, id);
            int i = statement.executeUpdate();
            return i;
        } else {
            return 0;
        }
    }


}

