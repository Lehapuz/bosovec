package DAO;

import Bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println("Пользователь - " + user.getName() + " успешно зарегистрирован");
    }

    public void read() {
        if (users.size() == 0){
            System.out.println("Пользователи отсутствуют");
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public List<User> getUsers() {
       return users;
        }

    public void updateUser(User user, User newUser) {
        users.removeIf(user1 -> user1.equals(user));
        users.add(newUser);
        System.out.println("Аккаунт пользователя - " + user.getName() + " успешно изменен на аккаунт - " + newUser.getName());
    }

    public void deleteUser(User user) {
        users.removeIf(user1 -> user1.equals(user));
        System.out.println("Аккаунт пользователя - " + user.getName() + " успешно удален");
    }

    public User findUserByEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
