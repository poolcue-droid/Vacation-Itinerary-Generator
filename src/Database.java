import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/traveldb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            System.out.println("🔄 Trying to connect...");

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("✅ Connected to MySQL DB");
            return conn;

        } catch (Exception e) {
            System.out.println("❌ CONNECTION FAILED:");
            e.printStackTrace();   // ⚠️ THIS IS KEY
            return null;
        }
    }
}
