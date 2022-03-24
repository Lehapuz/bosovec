package service;

import bean.User;
import dao.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class UserService {

    private final DataSource dataSource;
    private boolean isAuthorizated;
    private final Logger logger = LogManager.getRootLogger();
    private static int i = 0;


    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void registerUser(String name, String password, String email) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setRegTime(LocalDateTime.now());
        user.setId(++i);
        if (isValidEmail(user.getEmail())) {
            if (dataSource.getUserDAO().findUserByEmail(user.getEmail()) != null) {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            } else {
                dataSource.getUserDAO().addUser(user);
            }
        } else {
            logger.error("Неверный формат ввода");
        }
    }


    public void getAllUsers() {
        dataSource.getUserDAO().read();
    }


    public void deleteUserByEmail(String email, String password) {
        try {
            if (email.equals(dataSource.getUserDAO().findUserByEmail(email).getEmail())) {
                User user = dataSource.getUserDAO().findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    dataSource.getUserDAO().deleteUser(user);
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
            if (email.equals(dataSource.getUserDAO().findUserByEmail(email).getEmail())) {
                user = dataSource.getUserDAO().findUserByEmail(email);
                if (password.equals(user.getPassword())) {
                    user1.setEmail(user.getEmail());
                    user1.setId(user.getId());
                    dataSource.getUserDAO().updateUser(user, user1);
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
            if (email.equals(dataSource.getUserDAO().findUserByEmail(email).getEmail())) {
                User user = dataSource.getUserDAO().findUserByEmail(email);
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
