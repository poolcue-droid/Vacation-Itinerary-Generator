import java.sql.*;

public class Database {

    private static final String URL = "jdbc:sqlite:trip.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}