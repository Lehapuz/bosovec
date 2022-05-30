package by.epam.basavets.service;

import by.epam.basavets.bean.User;
import by.epam.basavets.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;
    private User authoriseUser;
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
            if (findUserByEmail(email) != null) {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            } else {
                userDAO.addUser(user);
            }
        } else {
            logger.error("Неверный формат ввода электронной почты");
        }
    }


    public List<User> getAllUsers() throws SQLException {
        return userDAO.read();
    }


    public void deleteUserByEmail(String email, String password) {
        try {
            if (email.equals(getAuthoriseUser().getEmail())) {
                if (password.equals(authoriseUser.getPassword())) {
                    userDAO.deleteUser(authoriseUser);
                    setAuthoriseUser(null);
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
            if (email.equals(authoriseUser.getEmail())) {
                if (password.equals(authoriseUser.getPassword())) {
                    int id = authoriseUser.getId();
                    authoriseUser.setId(id);
                    authoriseUser.setEmail(email);
                    authoriseUser.setName(name);
                    authoriseUser.setPassword(newPassword);
                    userDAO.updateUser(authoriseUser);
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


    public void authorizationUser(String email, String password) {
        try {
            if (email.equals(userDAO.findUserByEmail(email).getEmail())) {
                User user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    setAuthoriseUser(findUserByEmail(email));
                    logger.info("Авторизация пользователя прошла успешно");
                    logger.info(authoriseUser.toString());
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


    public User findUserByEmail(String email) throws SQLException {
        return userDAO.findUserByEmail(email);
    }


    public void exit() {
        setAuthoriseUser(null);
    }


    private boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }

    public void setAuthoriseUser(User authoriseUser) {
        this.authoriseUser = authoriseUser;
    }

    public User getAuthoriseUser(){
        return authoriseUser;
    }
}
