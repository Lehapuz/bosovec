package Service;

import Bean.User;
import DAO.UserDAO;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UserService {

    private final UserDAO userDAO;
    Scanner scanner = new Scanner(System.in);
    private boolean isRegistration;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void registerUser() {
        User user = new User();
        System.out.println("Введите имя");
        String input = scanner.nextLine();
        int i = 0;
        user.setName(input);
        System.out.println("Имя добавлено" + "\n" + "Введите пароль");
        input = scanner.nextLine();
        user.setPassword(input);
        System.out.println("пароль добавлен" + "\n" + "Введите адресс электронной почты");
        input = scanner.nextLine();
        user.setEmail(input);
        user.setRegTime(LocalDateTime.now());
        user.setId(++i);
        if (isValidEmail(user.getEmail()) && userDAO.getUsers().isEmpty()) {
            userDAO.addUser(user);
        }
        if (isValidEmail(user.getEmail()) && !userDAO.getUsers().isEmpty() && !user.getEmail()
                .equals(userDAO.findUserByEmail(user.getEmail()).getEmail())) {
            userDAO.addUser(user);
        } else {
            System.out.println("Неверный формат ввода или " +
                    "такой адрес электронной почты уже зарегистрирован");
        }
    }


    public void getAllUsers() {
        userDAO.read();
    }


    public void deleteUserByEmail() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                User user = userDAO.findUserByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    userDAO.deleteUser(user);
                    isRegistration = false;
                } else {
                    System.out.println("Адрес электронной почты не верный");
                }
            } else {
                System.out.println("Пароль неверный");
            }
        } catch (Exception e) {
            System.out.println("Пользователь не найден");
        }
    }


    public void updateUserByEmail() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            User user;
            User user1 = new User();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                user = userDAO.findUserByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    user1.setEmail(user.getEmail());
                    user.setId(user.getId());
                    userDAO.updateUser(user, user1);
                    System.out.println("Введите новое имя");
                    input = scanner.nextLine();
                    user1.setName(input);
                    System.out.println("Имя обновлено" + "\n" + "Введите новый пароль");
                    input = scanner.nextLine();
                    user1.setPassword(input);
                    System.out.println("пароль обновлен");
                    user1.setRegTime(LocalDateTime.now());
                } else {
                    System.out.println("Адрес электронной почты не верный");
                }
            } else {
                System.out.println("Пароль неверный");
            }
        } catch (Exception e) {
            System.out.println("Пользователь не найден");
        }
    }


    public void authorithationUser() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(userDAO.findUserByEmail(input).getEmail())) {
                User user = userDAO.findUserByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(user.getPassword())) {
                    System.out.println("Авторизация пользователя прошла успешно");
                }
                System.out.println(user.toString());
                isRegistration = true;
            } else {
                System.out.println("Авторизация не прошла");
            }
        } catch (Exception e) {
            System.out.println("Такого пользователя не существует");
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
