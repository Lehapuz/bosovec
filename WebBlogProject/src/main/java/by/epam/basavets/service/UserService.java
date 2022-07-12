package by.epam.basavets.service;

import by.epam.basavets.bean.User;


public interface UserService {

    void addRole(String secreteCode);

    void registerUser(String name, String password, String email);

    void deleteUserByEmail(User user, String email, String password);

    void updateUserByEmail(User user, String email, String password, String name, String newPassword);

    User authorizationUser(String email, String password);

    User findUserByEmail(String email);

    void setModeratorStatus(String postTitle, String status);

    boolean isValidEmail(String email);
}
