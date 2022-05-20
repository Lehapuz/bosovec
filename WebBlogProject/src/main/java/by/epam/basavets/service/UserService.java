package by.epam.basavets.service;

import by.epam.basavets.bean.User;
import by.epam.basavets.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;
    private boolean isAuthorizated;
    private final Logger logger = LogManager.getRootLogger();


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void registerUser(String name, String password, String email) throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        if (isValidEmail(user.getEmail())) {
            if (userDAO.findUserByEmail(user.getEmail()) != null) {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            } else {
                userDAO.addUser(user);
            }
        } else {
            logger.error("Неверный формат ввода электронной почты");
        }
    }


    public List<User> getAllUsers() throws SQLException {
        //userDAO.read();
        return userDAO.read();
    }


    public void deleteUserByEmail(String email, String password) {
        try {
            if (email.equals(userDAO.findUserByEmail(email).getEmail())) {
                User user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    userDAO.deleteUser(user);
                    isAuthorizated = false;
                } else {
                    logger.error("Адрес электронной почты не верный");
                }
            } else {
                logger.error("Пароль неверный");
            }
        } catch (Exception e) {
            logger.error("Пользователь не найден");
        }
    }


    public void updateUserByEmail(String email, String password, String name, String newPassword) {
        try {
            User user;
            if (email.equals(userDAO.findUserByEmail(email).getEmail())) {
                user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    int id = user.getId();
                    user.setId(id);
                    user.setEmail(email);
                    user.setName(name);
                    user.setPassword(newPassword);
                    userDAO.updateUser(user);
                } else {
                    logger.error("Адрес электронной почты не верный");
                }
            } else {
                logger.error("Пароль неверный");
            }
        } catch (Exception e) {
            logger.error("Пользователь не найден");
        }
    }


    public void authorithationUser(String email, String password) {
        try {
            if (email.equals(userDAO.findUserByEmail(email).getEmail())) {
                User user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    logger.info("Авторизация пользователя прошла успешно");
                    logger.info(user.toString());
                    isAuthorizated = true;
                } else {
                    logger.error("Пароль неверный");
                }
            } else {
                logger.error("Авторизация не прошла");
            }
        } catch (Exception e) {
            logger.error("Такого пользователя не существует");
        }
    }


    public void exit() {
        isAuthorizated = false;
    }


    public boolean getAuthorithated() {
        return isAuthorizated;
    }


    private boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }
}
