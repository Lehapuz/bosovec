package by.epam.basavets.service.impl;

import by.epam.basavets.bean.*;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.UserDAO;
import by.epam.basavets.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService implements by.epam.basavets.service.UserService {

    private final UserDAO userDAO;
    private final PostDAO postDAO;
    private final Logger logger = LogManager.getRootLogger();
    private final String SECRETE_CODE = "ADMIN";
    private final String OK = "OK";
    private final String NO = "NO";


    public UserService(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @Override
    public void addRole(String secreteCode) {
        Role role = new Role();
        if (secreteCode.equals(SECRETE_CODE)) {
            role.setRoleTypes(RoleTypes.MODERATOR);
        } else {
            role.setRoleTypes(RoleTypes.USER);
        }
        userDAO.addRole(role);
    }

    @Override
    public void registerUser(String name, String password, String email) {
        try {
            User user = new User();
            Role role = userDAO.getLastRole();
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(role);
            user.setActive(1);
            if (isValidEmail(user.getEmail())) {
                if (findUserByEmail(email) != null) {
                    logger.error("This email is already exist");
                } else {
                    userDAO.addUser(user);
                }
            } else {
                logger.error("Wrong email format");
            }
        } catch (Exception e) {
            logger.error("Can not add user");
            throw new ServiceException("Can not add user", e);
        }
    }

    @Override
    public void deleteUserByEmail(User user, String email, String password) {
        try {
            if (email.equals(user.getEmail())) {
                if (password.equals(user.getPassword())) {
                    user.setActive(-1);
                    userDAO.deleteUser(user);
                } else {
                    logger.error("Wrong email");
                }
            } else {
                logger.error("Wrong password");
            }
        } catch (Exception e) {
            logger.error("Can not delete user");
            throw new ServiceException("Can not delete user", e);
        }
    }

    @Override
    public void updateUserByEmail(User user, String email, String password, String name, String newPassword) {
        try {
            if (email.equals(user.getEmail())) {
                if (password.equals(user.getPassword())) {
                    user.setEmail(email);
                    user.setName(name);
                    user.setPassword(newPassword);
                    userDAO.updateUser(user);
                } else {
                    logger.error("Wrong email");
                }
            } else {
                logger.error("Wrong password");
            }
        } catch (Exception e) {
            logger.error("Can not update user");
            throw new ServiceException("Can not update user", e);
        }
    }

    @Override
    public User authorizationUser(String email, String password) {
        User user;
        User authoriseUser = null;
        try {
            if (userDAO.findUserByEmail(email) != null && email.equals(userDAO.findUserByEmail(email).getEmail())) {
                user = userDAO.findUserByEmail(email);
                if (password.equals(user.getPassword()) && user.getActive() == 1) {
                    authoriseUser = user;
                    logger.info("Authorization is successful");
                    logger.info(user.toString());
                } else {
                    logger.error("Wrong password or user deleted");
                }
            } else {
                logger.error("Wrong email");
            }
        } catch (Exception e) {
            logger.error("Can not authorize user");
            throw new ServiceException("Can not authorize user", e);
        }
        return authoriseUser;
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public void setModeratorStatus(String postTitle, String status) {
        try {
            Post post;
            if (postTitle.equals(postDAO.findPostByTitle(postTitle).getTitle())) {
                post = postDAO.findPostByTitle(postTitle);
                if (status.equals(OK)) {
                    int id = post.getId();
                    post.setId(id);
                    post.setModeratorStatus(ModeratorStatus.ACCEPTED);
                    postDAO.updatePostModeratorStatus(post);
                    logger.info(("Status changed"));
                }
                if (status.equals(NO)) {
                    int id = post.getId();
                    post.setId(id);
                    post.setModeratorStatus(ModeratorStatus.DECLINED);
                    postDAO.updatePostModeratorStatus(post);
                    logger.info(("Status changed"));
                }
            }
        } catch (Exception e) {
            logger.error("Can not set moderator status");
            throw new ServiceException("Can not set moderator status", e);
        }
    }

    @Override
    public boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }
}
