package by.epam.basavets.dao;

import by.epam.basavets.DBConnection;
import by.epam.basavets.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

    private final List<User> users = new ArrayList<>();
    private final Logger logger = LogManager.getRootLogger();
    private static final long serialVersionUID = 17L;


    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, registration_time) " +
                "VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setString(4, LocalDateTime.now().toString());
        statement.execute();
        logger.info("Пользователь - " + user.getName() + " успешно зарегистрирован");
    }


    public void read() throws SQLException {
        String sql = "SELECT * FROM users";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet == null) {
            logger.info("Пользователи отсутствуют");
        } else {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                logger.info(user.toString());
            }
        }
    }


    public List<User> getUsers() {
        return users;
    }


    public void updateUser(User user) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getId());
        statement.executeUpdate();

        logger.info("Аккаунт успешно изменен на аккаунт - " + user.getName());
    }


    public void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.execute();
        logger.info("Аккаунт пользователя - " + user.getName() + " успешно удален");
    }


    public User findUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            return user;
        }
        return null;
    }

    public static UserDAO getInstance(){
        return getInstance();
    }
}