package by.epam.basavets.dao;

import by.epam.basavets.bean.User;
import by.epam.basavets.utils.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDAO {


    private final Logger logger = LogManager.getRootLogger();


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
        statement.close();
        connection.close();
    }


    public void read() throws SQLException {
        String sql = "SELECT * FROM users";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
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
        if (!resultSet.next()) {
            logger.info("Пользователи отсутствуют");
        }
        resultSet.close();
        statement.close();
        connection.close();
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
        statement.close();
        connection.close();
    }


    public void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.execute();
        logger.info("Аккаунт пользователя - " + user.getName() + " успешно удален");
        statement.close();
        connection.close();
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
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }
}
