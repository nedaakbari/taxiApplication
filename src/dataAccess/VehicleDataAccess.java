
package dataAccess;

import enumration.VehicleType;
import models.Vehicle.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleDataAccess extends DataAccess {
    public VehicleDataAccess() {
    }

    public int save(Vehicle vehicle) throws SQLException {
        if (connection != null) {
            String query = "INSERT INTO vehicle (`type`, `model`, `color`, `plaque`) VALUES ( ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, VehicleType.getValuetoString(vehicle.getTypeOfVehicle()));
            statement.setString(2, vehicle.getModel());
            statement.setString(3, vehicle.getColor());
            statement.setString(4, vehicle.getPlaque());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0)
                return rowsInserted;
        }
        return 0;
    }




    public  Vehicle getVehicleById(int id) throws SQLException {
        if (connection != null) {
            Vehicle vehicle = new Vehicle();
            PreparedStatement statement = connection.prepareStatement("select * from vehicle where id = ?");
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                vehicle.setTypeOfVehicle(VehicleType.getValue(resultset.getString("type")));
                vehicle.setModel(resultset.getString("model"));
                vehicle.setColor(resultset.getString("color"));
                vehicle.setPlaque(resultset.getString("plaque"));
                vehicle.setId(id);
            }
            return vehicle;
        }
        return null;
    }

    public Vehicle findVehicleIdByPlaque(String plaque) throws SQLException {
        if (connection != null) {
            String query = "SELECT * FROM vehicle where plaque=?";
            PreparedStatement findID = connection.prepareStatement(query);
            findID.setString(1, plaque);
            ResultSet resultSet = findID.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle(resultSet.getInt("id"));
                return vehicle;
            }

        }
        return null;
    }


}

