import java.sql.*;

public class Singleton extends DatabaseConnection {

    private static Singleton instance;
    private final String url = "jdbc:mysql://localhost:3306/skillbox";
    private final String user = "root";
    private final String pass = "Energotehno202111101983KE7";

    private Singleton() throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.DataBaseConnection(url, user, pass);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
