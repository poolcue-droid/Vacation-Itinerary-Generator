import java.sql.*;
import java.util.*;

public class ItineraryGenerator {

    public static List<Hotel> getHotels(String destination, int budget, String sortOrder) {
        List<Hotel> hotels = new ArrayList<>();
            try {
                Connection conn = Database.connect();
                String query = "SELECT * FROM hotels WHERE location=? AND price<=?";
                if(sortOrder != null) {
                if(sortOrder.equalsIgnoreCase("asc")) query += " ORDER BY price ASC";
                else if(sortOrder.equalsIgnoreCase("desc")) query += " ORDER BY price DESC";
            }

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, destination);
            ps.setInt(2, budget);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                hotels.add(new Hotel(
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image")
                ));
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return hotels;
    }

    public static List<Activity> getActivities(String destination, int budget, String typeFilter) {
        List<Activity> activities = new ArrayList<>();
        try {
            Connection conn = Database.connect();
            String query = "SELECT * FROM activities WHERE location=? AND cost<=?";
            if(typeFilter != null && !typeFilter.isEmpty()) query += " AND type=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, destination);
            ps.setInt(2, budget);
            if(typeFilter != null && !typeFilter.isEmpty()) ps.setString(3, typeFilter);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                activities.add(new Activity(
                rs.getString("name"),
                rs.getInt("cost"),
                rs.getString("image"),
                rs.getString("type")
            ));
            }

            // Shuffle for random day-wise assignment
            Collections.shuffle(activities);

        } catch (Exception e) {
        e.printStackTrace();
        }
        return activities;
    }
}