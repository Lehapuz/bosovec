package by.epam.basavets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {


    private static final Logger logger = LogManager.getRootLogger();
    private static DBConnection dbConnection = new DBConnection();
    private static String URL;
    private static String USER;
    private static String PASS;


    private void getResource() throws IOException {
        Properties properties = new Properties();
        String propertiesFileName = "connection.properties";
        properties.load(getClass().getClassLoader().getResourceAsStream(propertiesFileName));
        URL = properties.getProperty("url");
        USER = properties.getProperty("username");
        PASS = properties.getProperty("password");
        logger.info("Настройки БД получены");
    }


    public static Connection getConnection() throws SQLException {
        try {
            dbConnection.getResource();
        } catch (IOException e) {
            logger.error("Настройки БД не получены");
        }

        Connection connection = DriverManager.getConnection(URL, USER, PASS);

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users(" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "registration_time DATETIME NOT NULL, " +
                "PRIMARY KEY(id))");

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS moderators(" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "registration_time DATETIME NOT NULL, " +
                "PRIMARY KEY(id))");

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS posts(" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "title TEXT NOT NULL, " +
                "text TEXT NOT NULL, " +
                "like_count INT, " +
                "dislike_count INT, " +
                "view_count INT, " +
                "time DATETIME NOT NULL, " +
                "user_id INT NOT NULL, " +
                "moderator_status TEXT NOT NULL, " +
                "PRIMARY KEY(id))");

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS post_comments(" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "text TEXT NOT NULL, " +
                "time DATETIME NOT NULL, " +
                "post_id INT NOT NULL, " +
                "user_id INT NOT NULL, " +
                "PRIMARY KEY(id))");

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS post_votes(" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "value INT NOT NULL, " +
                "time DATETIME NOT NULL, " +
                "post_id INT NOT NULL, " +
                "user_id INT NOT NULL, " +
                "PRIMARY KEY(id))");

        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS settings(" +
                "setting_status VARCHAR(5) NOT NULL, " +
                "PRIMARY KEY(setting_status))"
        );

        return connection;
    }
}
