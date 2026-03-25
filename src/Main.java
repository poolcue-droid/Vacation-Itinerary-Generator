import java.util.*;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Vacation Itinerary Generator ===");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Destination (Kerala/Delhi/Manali): ");
            String destination = sc.nextLine();

            System.out.print("Budget: ");
            int budget = sc.nextInt();

            System.out.print("Number of Days: ");
            int days = sc.nextInt();

            User user = new User(name, destination, budget, days);

            int perDayBudget = budget / days;
            int hotelBudget = perDayBudget;
            int activityBudget = perDayBudget;

            // Updated calls with sort/filter options
            String sortOrder = "asc"; // Example: ascending price for hotels
            String typeFilter = null;  // null = no filter on activities

            List<Hotel> hotels = ItineraryGenerator.getHotels(destination, hotelBudget, sortOrder);
            List<Activity> activities = ItineraryGenerator.getActivities(destination, activityBudget, typeFilter);

            System.out.println("\n--- Recommended Hotels ---");
            if(hotels.isEmpty()) System.out.println("No hotels available within budget.");
            else hotels.forEach(h -> System.out.println(h.name + " - ₹" + h.pricePerNight));

            System.out.println("\n--- Activities ---");
            if(activities.isEmpty()) System.out.println("No activities available within budget.");
            else activities.forEach(a -> System.out.println(a.name + " - ₹" + a.cost));

            System.out.println("\n=== Day-wise Plan ===");
            Random rand = new Random();
            for (int i = 1; i <= days; i++) {
                Activity activity = activities.isEmpty() ? null : activities.get(rand.nextInt(activities.size()));
                String activityName = activity == null ? "No Activity" : activity.name;
                System.out.println("Day " + i + ": Explore " + destination + " + " + activityName);
            }
        }
    }
}