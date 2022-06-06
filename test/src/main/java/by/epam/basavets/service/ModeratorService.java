package by.epam.basavets.service;

import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Moderator;
import by.epam.basavets.bean.Post;
import by.epam.basavets.dao.ModeratorDAO;
import by.epam.basavets.dao.PostDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ModeratorService {

    private final ModeratorDAO moderatorDAO;
    private final PostDAO postDAO;
    private boolean isAuthorithated;
    private final Logger logger = LogManager.getRootLogger();

    public ModeratorService(ModeratorDAO moderatorDAO, PostDAO postDAO) {
        this.moderatorDAO = moderatorDAO;
        this.postDAO = postDAO;
    }


    public void registerModerator(String name, String password, String email) throws SQLException {
        Moderator moderator = new Moderator();
        moderator.setName(name);
        moderator.setPassword(password);
        moderator.setEmail(email);
        if (isValidEmail(moderator.getEmail())) {
            if (moderatorDAO.findModeratorByEmail(moderator.getEmail()) != null) {
                logger.error("Такой адрес электронной почты уже зарегистрирован");
            } else {
                moderatorDAO.addModerator(moderator);
            }
        } else {
            logger.error("Неверный формат ввода");
        }
    }


    public void getAllModerators() throws SQLException {
        moderatorDAO.read();
    }


    public void deleteModeratorByEmail(String email, String password) {
        try {

            if (email.equals(moderatorDAO.findModeratorByEmail(email).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(email);
                if (password.equals(moderator.getPassword())) {
                    moderatorDAO.deleteModerator(moderator);
                    isAuthorithated = false;
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


    public void updateModeratorByEmail(String email, String password, String name, String newPassword) {
        try {
            Moderator moderator;
            if (email.equals(moderatorDAO.findModeratorByEmail(email).getEmail())) {
                moderator = moderatorDAO.findModeratorByEmail(email);
                if (password.equals(moderator.getPassword())) {
                    int id = moderator.getId();
                    moderator.setId(id);
                    moderator.setEmail(email);
                    moderator.setName(name);
                    moderator.setPassword(newPassword);
                    moderatorDAO.updateModerator(moderator);
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


    public void authorithationModerator(String email, String password) {
        try {
            if (email.equals(moderatorDAO.findModeratorByEmail(email).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(email);
                if (password.equals(moderator.getPassword())) {
                    logger.info("Авторизация пользователя прошла успешно");
                    logger.info(moderator.toString());
                    isAuthorithated = true;
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


    public void setModeratorStatus(String postTitle, String status) {
        try {
            Post post;
            if (postTitle.equals(postDAO.findPostByTitle(postTitle).getTitle())) {
                post = postDAO.findPostByTitle(postTitle);
                if (status.equals("OK")) {
                    int id = post.getId();
                    post.setId(id);
                    post.setModeratorStatus(ModeratorStatus.ACCEPTED);
                    postDAO.updatePostModeratorStatus(post);
                    logger.info(("Статус изменен"));
                }
                if (status.equals("NO")) {
                    int id = post.getId();
                    post.setId(id);
                    post.setModeratorStatus(ModeratorStatus.DECLINED);
                    postDAO.updatePostModeratorStatus(post);
                    logger.info(("Статус изменен"));
                }
            }
        } catch (Exception e) {
            logger.error("Пост не найден");
        }
    }


    public void exit() {
        isAuthorithated = false;
    }


    public boolean getAuthorithated() {
        return isAuthorithated;
    }


    private boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }
}
