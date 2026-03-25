import java.util.List;

public class Trip {

    User user;
    List<Hotel> hotels;
    List<Activity> activities;

    public Trip(User user, List<Hotel> hotels, List<Activity> activities) {
        this.user = user;
        this.hotels = hotels;
        this.activities = activities;
    }

    public void displayTripDetails() {

        System.out.println("\n===== FINAL ITINERARY =====");

        System.out.println("Name: " + user.name);
        System.out.println("Destination: " + user.destination);
        System.out.println("Budget: ₹" + user.budget);
        System.out.println("Days: " + user.days);

        System.out.println("\n--- Recommended Hotels ---");
        if (hotels.isEmpty()) {
            System.out.println("No hotels found within budget.");
        } else {
            for (Hotel h : hotels) {
                System.out.println(h.name + " - ₹" + h.pricePerNight);
            }
        }

        System.out.println("\n--- Activities ---");
        if (activities.isEmpty()) {
            System.out.println("No activities found within budget.");
        } else {
            for (Activity a : activities) {
                System.out.println(a.name + " - ₹" + a.cost);
            }
        }

        System.out.println("\n=== Day-wise Plan ===");
        for (int i = 1; i <= user.days; i++) {
            System.out.println("Day " + i + ": Explore " + user.destination + " + Activity");
        }
    }
}