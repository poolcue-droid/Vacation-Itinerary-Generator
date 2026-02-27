import java.sql.*;
import java.util.*;

public class ItineraryGenerator {

    public static List<Hotel> getHotels(String destination, int maxBudgetPerNight) {
        List<Hotel> hotels = new ArrayList<>();

        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM hotels WHERE destination = ? AND price_per_night <= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, destination);
            stmt.setInt(2, maxBudgetPerNight);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getString("name"),
                        rs.getInt("price_per_night")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public static List<Activity> getActivities(String destination, int maxCost) {
        List<Activity> activities = new ArrayList<>();

        try (Connection conn = Database.connect()) {
            String query = "SELECT * FROM activities WHERE destination = ? AND cost <= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, destination);
            stmt.setInt(2, maxCost);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                activities.add(new Activity(
                        rs.getString("name"),
                        rs.getInt("cost")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return activities;
    }
}