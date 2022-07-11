package by.epam.basavets.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private BlockingQueue<Connection> connectionBlockingQueue;
    private BlockingQueue<Connection> givenAwayConnectionQueue;
    private final String url;
    private final String user;
    private final String password;
    private final int poolSize;
    private static ConnectionPool instance;
    private final Logger logger = LogManager.getRootLogger();


    private ConnectionPool() throws IOException, SQLException {
        DBResources dbResources = DBResources.getInstance();
        dbResources.getResource();
        this.url = dbResources.getURL();
        this.user = dbResources.getUser();
        this.password = dbResources.getPASS();
        this.poolSize = dbResources.getPoolSize();
        initPoolData();
    }

    public void initPoolData() {
        try {
            givenAwayConnectionQueue = new ArrayBlockingQueue<>(poolSize);
            connectionBlockingQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionBlockingQueue.add(connection);
                logger.info("add connection");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }


    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionBlockingQueue.take();
            logger.info(connectionBlockingQueue.size() + " - Size of free pool");

            // If tables are not created
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS roles(" +
//                    "id INT NOT NULL AUTO_INCREMENT, " +
//                    "type TEXT NOT NULL, " +
//                    "PRIMARY KEY(id))");
//
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users(" +
//                    "id INT NOT NULL AUTO_INCREMENT, " +
//                    "name TEXT NOT NULL, " +
//                    "email TEXT NOT NULL, " +
//                    "password TEXT NOT NULL, " +
//                    "registration_time DATETIME NOT NULL, " +
//                    "role_id INT NOT NULL, " +
//                    "active INT NOT NULL, " +
//                    "PRIMARY KEY(id), " +
//                    "FOREIGN KEY(role_id) REFERENCES roles(id))");
//
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS posts(" +
//                    "id INT NOT NULL AUTO_INCREMENT, " +
//                    "title TEXT NOT NULL, " +
//                    "text TEXT NOT NULL, " +
//                    "like_count INT, " +
//                    "dislike_count INT, " +
//                    "view_count INT, " +
//                    "time DATETIME NOT NULL, " +
//                    "user_id INT NOT NULL, " +
//                    "moderator_status TEXT NOT NULL, " +
//                    "PRIMARY KEY(id), " +
//                    "FOREIGN KEY(user_id) REFERENCES users(id))");
//
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS post_comments(" +
//                    "id INT NOT NULL AUTO_INCREMENT, " +
//                    "text TEXT NOT NULL, " +
//                    "time DATETIME NOT NULL, " +
//                    "post_id INT NOT NULL, " +
//                    "user_id INT NOT NULL, " +
//                    "PRIMARY KEY(id), " +
//                    "FOREIGN KEY(post_id) REFERENCES posts(id), " +
//                    "FOREIGN KEY(user_id) REFERENCES users(id))");
//
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS post_votes(" +
//                    "id INT NOT NULL AUTO_INCREMENT, " +
//                    "value INT NOT NULL, " +
//                    "time DATETIME NOT NULL, " +
//                    "post_id INT NOT NULL, " +
//                    "user_id INT NOT NULL, " +
//                    "PRIMARY KEY(id), " +
//                    "FOREIGN KEY(post_id) REFERENCES posts(id), " +
//                    "FOREIGN KEY(user_id) REFERENCES users(id))");
//
//            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS settings(" +
//                    "setting_status VARCHAR(5) NOT NULL, " +
//                    "PRIMARY KEY(setting_status))");

            givenAwayConnectionQueue.add(connection);
            logger.info(givenAwayConnectionQueue.size() + " - Size of busy pool");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }


    public void givenAwayConnection(Connection connection, Statement statement, ResultSet resultSet) throws InterruptedException {
        try {
            resultSet.close();
        } catch (SQLException e) {
            logger.error("ResultSet not closed");
        }
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Statement not closed");
        }
        givenAwayConnectionQueue.take();
        connectionBlockingQueue.add(connection);
        logger.info(connectionBlockingQueue.size() + " - Size of free pool " + givenAwayConnectionQueue.size()
                + " - Size of busy pool");
    }


    public void givenAwayConnection(Connection connection, Statement statement) throws InterruptedException {
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Statement not closed");
        }
        givenAwayConnectionQueue.take();
        connectionBlockingQueue.add(connection);
        logger.info(connectionBlockingQueue.size() + " - Size of free pool " + givenAwayConnectionQueue.size()
                + " - Size of busy pool");
    }


    public static ConnectionPool getInstance() throws IOException, SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
}
