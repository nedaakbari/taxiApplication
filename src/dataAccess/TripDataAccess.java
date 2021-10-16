package dataAccess;

import enumration.Gender;
import enumration.PayBy;
import models.Location;
import models.Trip;
import models.user.Passenger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripDataAccess extends DataAccess {

    public int save(Trip trip) throws SQLException {
        if (connection != null) {
            String query = "INSERT INTO `taxi`.`trip` (`passenger_id`, `Driver_id`, `origin`, `destination`, `cost`, `payBy`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trip.getPassenger().getId());
            statement.setInt(2, trip.getDriver().getId());
            String origin = trip.getOrigin().getX() + "," + trip.getOrigin().getY();
            String destination = trip.getDestination().getX() + "," + trip.getDestination().getY();
            statement.setString(3, origin);
            statement.setString(4, destination);
            statement.setInt(5, trip.getCost());
            String payBy = PayBy.getValuetoString(trip.getPayBy());
            statement.setString(6, payBy);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new passeneger  inserted successfully!");
                return rowsInserted;
            }
        }
        return 0;
    }


}
