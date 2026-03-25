public class Activity {
    String name;
    int cost;
    String type;    // NEW
    String image;   // NEW

    public Activity(String name, int cost, String image, String type) {
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.type = type;
    }
}