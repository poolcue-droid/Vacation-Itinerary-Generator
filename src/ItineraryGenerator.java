import java.sql.*;
import java.util.*;

public class ItineraryGenerator {

    public static List<Hotel> getHotels(String destination, int budget) {
        List<Hotel> hotels = new ArrayList<>();

        try {
            System.out.println("getHotels() called");
            System.out.println("Destination: " + destination + ", Budget: " + budget);

            Connection conn = Database.connect();

            if (conn == null) {
                System.out.println("❌ DB Connection is NULL");
                return hotels;
            }

            String query = "SELECT * FROM hotels WHERE LOWER(location)=LOWER(?) AND price <= ? ORDER BY price ASC";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, destination.trim());
            ps.setInt(2, budget);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");

                System.out.println("Hotel Found: " + name); // DEBUG

                hotels.add(new Hotel(name, price));
            }

            if (hotels.isEmpty()) {
                System.out.println("⚠ No hotels found in DB");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotels;
    }

    public static List<Activity> getActivities(String destination, int budget) {
        List<Activity> activities = new ArrayList<>();

        try {
            System.out.println("getActivities() called");
            System.out.println("Destination: " + destination + ", Budget: " + budget);

            Connection conn = Database.connect();

            if (conn == null) {
                System.out.println("❌ DB Connection is NULL");
                return activities;
            }

            String query = "SELECT * FROM activities WHERE LOWER(location)=LOWER(?) AND cost <= ? ORDER BY cost ASC";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, destination.trim());
            ps.setInt(2, budget);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int cost = rs.getInt("cost");

                System.out.println("Activity Found: " + name); // DEBUG

                activities.add(new Activity(name, cost));
            }

            if (activities.isEmpty()) {
                System.out.println("⚠ No activities found in DB");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return activities;
    }
}