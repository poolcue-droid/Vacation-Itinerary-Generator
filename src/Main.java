import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Vacation Itinerary Generator ===");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Destination: ");
        String destination = sc.nextLine();

        System.out.print("Budget: ");
        int budget = sc.nextInt();

        System.out.print("Number of Days: ");
        int days = sc.nextInt();

        System.out.print("Travelers: ");
        int travelers = sc.nextInt();

        User user = new User(name, destination, budget, days, travelers);

        int perDayBudget = budget / days;
        int hotelBudget = perDayBudget / 2;
        int activityBudget = perDayBudget / 2;

        var hotels = ItineraryGenerator.getHotels(destination, hotelBudget);
        var activities = ItineraryGenerator.getActivities(destination, activityBudget);

        System.out.println("\n--- Recommended Hotels ---");
        hotels.forEach(h -> System.out.println(h.name + " - ₹" + h.pricePerNight));

        System.out.println("\n--- Activities ---");
        activities.forEach(a -> System.out.println(a.name + " - ₹" + a.cost));

        System.out.println("\n=== Day-wise Plan ===");
        for (int i = 1; i <= days; i++) {
            System.out.println("Day " + i + ": Explore " + destination + " + Activity");
        }

        sc.close();
    }
}