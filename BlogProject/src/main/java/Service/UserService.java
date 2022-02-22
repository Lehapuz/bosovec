package Service;

import Bean.User;
import DAO.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserService {

    private final UserDAO userDAO;
    Scanner scanner = new Scanner(System.in);
    private boolean isRegistration;
    private final Logger logger = LogManager.getRootLogger();


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void registerUser() {
        User user = new User();
        logger.info("Введите имя");
        String input = scanner.nextLine();
        int i = 0;
        user.setName(input);
        logger.info("Имя добавлено" + "\n" + "Введите пароль");
        input = scanner.nextLine();
        user.setPassword(input);
        logger.info("пароль добавлен" + "\n" + "Введите адресс электронной почты");
        input = scanner.nextLine();
        user.setEmail(input);
        user.setRegTime(LocalDateTime.now());
        user.setId(++i);
        if (isValidEmail(user.getEmail())) {
            if (userDAO.getUsers().isEmpty() || !userDAO.findUserByEmail(input).getEmail().equals(user.getEmail())) {
                System.out.println(userDAO);
                userDAO.addUser(user);
            } else {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            }
        } else {
            logger.error("Неверный формат ввода");
        }
    }


    public void getAllUsers() {
        userDAO.read();
    }


    public void deleteUserByEmail() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                User user = userDAO.findUserByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    userDAO.deleteUser(user);
                    isRegistration = false;
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


    public void updateUserByEmail() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            User user;
            User user1 = new User();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                user = userDAO.findUserByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    user1.setEmail(user.getEmail());
                    user.setId(user.getId());
                    userDAO.updateUser(user, user1);
                    logger.info("Введите новое имя");
                    input = scanner.nextLine();
                    user1.setName(input);
                    logger.info("Имя обновлено" + "\n" + "Введите новый пароль");
                    input = scanner.nextLine();
                    user1.setPassword(input);
                    logger.info("пароль обновлен");
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


    public void authorithationUser() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                User user = userDAO.findUserByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    logger.info("Авторизация пользователя прошла успешно");
                }
                System.out.println(user.toString());
                isRegistration = true;
            } else {
                logger.error("Авторизация не прошла");
            }
        } catch (Exception e) {
            logger.error("Такого пользователя не существует");
        }
    }


    public void exit() {
        isRegistration = false;
    }


    public boolean getRegistration() {
        return isRegistration;
    }


    private boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }
}
