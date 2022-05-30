package by.epam.basavets.dao;

import by.epam.basavets.utils.DBConnection;
import by.epam.basavets.bean.Moderator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModeratorDAO {

    private final Logger logger = LogManager.getRootLogger();


    public void addModerator(Moderator moderator) throws SQLException {
        String sql = "INSERT INTO moderators (name, email, password, registration_time) " +
                "VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, moderator.getName());
        statement.setString(2, moderator.getEmail());
        statement.setString(3, moderator.getPassword());
        statement.setString(4, LocalDateTime.now().toString());
        statement.execute();
        logger.info("Модератор - " + moderator.getName() + " успешно зарегистрирован");
        statement.close();
        connection.close();
    }


    public List<Moderator> read() throws SQLException {
        List<Moderator> moderators = new ArrayList<>();
        String sql = "SELECT * FROM moderators";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Moderator moderator = new Moderator();
            moderator.setId(resultSet.getInt("id"));
            moderator.setName(resultSet.getString("name"));
            moderator.setEmail(resultSet.getString("email"));
            moderator.setPassword(resultSet.getString("password"));
            moderator.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            logger.info(moderator.toString());
            moderators.add(moderator);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return moderators;
    }


    public void updateModerator(Moderator moderator) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE moderators SET name = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, moderator.getName());
        statement.setString(2, moderator.getEmail());
        statement.setString(3, moderator.getPassword());
        statement.setInt(4, moderator.getId());
        statement.executeUpdate();
        logger.info("Аккаунт успешно изменен на аккаунт - " + moderator.getName());
        statement.close();
        connection.close();
    }


    public void deleteModerator(Moderator moderator) throws SQLException {
        String sql = "DELETE FROM moderators WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, moderator.getEmail());
        statement.execute();
        logger.info("Аккаунт пользователя - " + moderator.getName() + " успешно удален");
        statement.close();
        connection.close();
    }


    public Moderator findModeratorByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM moderators WHERE email = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Moderator moderator = new Moderator();
            moderator.setId(resultSet.getInt("id"));
            moderator.setName(resultSet.getString("name"));
            moderator.setEmail(resultSet.getString("email"));
            moderator.setPassword(resultSet.getString("password"));
            moderator.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            return moderator;
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }
}
