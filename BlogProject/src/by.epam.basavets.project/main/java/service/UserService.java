package service;

import bean.User;
import dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class UserService {

    private final UserDAO userDAO;
    private boolean isAuthorizated;
    private final Logger logger = LogManager.getRootLogger();


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void registerUser(String name, String password, String email) {
        User user = new User();
        int i = 0;
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setRegTime(LocalDateTime.now());
        user.setId(++i);
        if (isValidEmail(user.getEmail())) {
            if (userDAO.findUserByEmail(user.getEmail()) != null) {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            } else {
                userDAO.addUser(user);
            }
        } else {
            logger.error("Неверный формат ввода");
        }
    }


    public void getAllUsers() {
        userDAO.read();
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
            User user1 = new User();
            if (email.equals(userDAO.findUserByEmail(email).getEmail())) {
                user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    user1.setEmail(user.getEmail());
                    user1.setId(user.getId());
                    userDAO.updateUser(user, user1);
                    user1.setName(name);
                    user1.setPassword(newPassword);
                    user1.setRegTime(LocalDateTime.now());
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
                }
                logger.info(user.toString());
                isAuthorizated = true;
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
