package by.epam.basavets.dao.impl;

import by.epam.basavets.bean.RoleTypes;
import by.epam.basavets.bean.Role;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.ConnectionPool;
import by.epam.basavets.dao.DAOException;
import by.epam.basavets.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDao {

    private final Logger logger = LogManager.getRootLogger();


    @Override
    public void addRole(Role role) {
        String sql = "INSERT INTO roles (type) VALUES (?)";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getRoleTypes().toString());
            statement.execute();
            logger.info("Роль добавлена");
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add role");
            throw new DAOException("Can not add role", e);
        }
    }


    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, password, registration_time, role_id, active) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, LocalDateTime.now().toString());
            statement.setInt(5, user.getRole().getId());
            statement.setInt(6, user.getActive());
            statement.execute();
            logger.info("Пользователь - " + user.getName() + " успешно зарегистрирован");
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add user");
            throw new DAOException("Can not add user", e);
        }
    }


    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
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
                user.setRole(findRoleById(resultSet.getInt("role_id")));
                user.setActive(resultSet.getInt("active"));
                logger.info(user.toString());
                users.add(user);
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not read users");
            throw new DAOException("Can not read users", e);
        }
        return users;
    }


    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role_id = ?, active = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole().getId());
            statement.setInt(5, user.getActive());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            logger.info("Аккаунт успешно изменен на аккаунт - " + user.getName());
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update user");
            throw new DAOException("Can not update user", e);
        }
    }


    @Override
    public void deleteUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role_id = ?, active = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole().getId());
            statement.setInt(5, user.getActive());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            statement.execute();
            logger.info("Аккаунт пользователя - " + user.getName() + " успешно удален");
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not delete user");
            throw new DAOException("Can not delete user", e);
        }
    }


    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                user.setRole(findRoleById(resultSet.getInt("role_id")));
                user.setActive(resultSet.getInt("active"));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find user");
            throw new DAOException("Can not find user", e);
        }
        return user;
    }


    @Override
    public User findUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find user for post");
            throw new DAOException("Can not find user for post", e);
        }
        return user;
    }


    @Override
    public Role findRoleById(int id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        Role role = null;
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRoleTypes(RoleTypes.valueOf(resultSet.getString("type")));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find user");
            throw new DAOException("Can not find user", e);
        }
        return role;
    }


    @Override
    public Role getLastRole() {
        Role role = null;
        String sql = "SELECT * FROM roles";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.isLast()) {
                    role = new Role();
                    role.setId(resultSet.getInt("id"));
                    role.setRoleTypes(RoleTypes.valueOf(resultSet.getString("type")));
                }
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not get last role");
            throw new DAOException("Can not get last role", e);
        }
        return role;
    }
}




