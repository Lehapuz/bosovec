package by.epam.basavets.dao;

import by.epam.basavets.bean.Role;
import by.epam.basavets.bean.User;

import java.util.List;

public interface UserDao {

    void addRole(Role role);

    void addUser(User user);

    List<User> read();

    void updateUser(User user);

    void deleteUser(User user);

    User findUserByEmail(String email);

    User findUserById(int id);

    Role findRoleById(int id);

    Role getLastRole();
}
