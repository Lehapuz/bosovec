package dao;

import bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

    private final List<User> users = new ArrayList<>();
    private final Logger logger = LogManager.getRootLogger();
    private static final long serialVersionUID = 17L;

    public void addUser(User user) {
        users.add(user);
        logger.info("Пользователь - " + user.getName() + " успешно зарегистрирован");
    }

    public void read() {
        if (users.size() == 0) {
            logger.info("Пользователи отсутствуют");
        }
        for (User user : users) {
            logger.info(user.toString());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void updateUser(User user, User newUser) {
        users.removeIf(user1 -> user1.equals(user));
        users.add(newUser);
        logger.info("Аккаунт пользователя - " + user.getName() + " успешно изменен на аккаунт - " + newUser.getName());
    }

    public void deleteUser(User user) {
        users.removeIf(user1 -> user1.equals(user));
        logger.info("Аккаунт пользователя - " + user.getName() + " успешно удален");
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
