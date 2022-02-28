package service;

import bean.Enum.ModeratorStatus;
import bean.Moderator;
import bean.Post;
import dao.ModeratorDAO;
import dao.PostDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class ModeratorService {

    private final ModeratorDAO moderatorDAO;
    private final PostDAO postDAO;
    private boolean isAuthorithated;
    private final Logger logger = LogManager.getRootLogger();

    public ModeratorService(ModeratorDAO moderatorDAO, PostDAO postDAO) {
        this.moderatorDAO = moderatorDAO;
        this.postDAO = postDAO;
    }


    public void registerModerator(String name, String password, String email) {
        Moderator moderator = new Moderator();
        int i = 0;
        moderator.setName(name);
        moderator.setPassword(password);
        moderator.setEmail(email);
        moderator.setRegTime(LocalDateTime.now());
        moderator.setId(++i);
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


    public void getAllModerators() {
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
            Moderator moderator1 = new Moderator();
            if (email.equals(moderatorDAO.findModeratorByEmail(email).getEmail())) {
                moderator = moderatorDAO.findModeratorByEmail(email);
                if (password.equals(moderator.getPassword())) {
                    moderator1.setEmail(moderator.getEmail());
                    moderator1.setId(moderator.getId());
                    moderatorDAO.updateModerator(moderator, moderator1);
                    moderator1.setName(name);
                    moderator1.setPassword(newPassword);
                    moderator1.setRegTime(LocalDateTime.now());
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
                }
                System.out.println(moderator.toString());
                isAuthorithated = true;
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
                    post.setModeratorStatus(ModeratorStatus.ACCEPTED);
                    logger.info(("Статус изменен"));
                }
                if (status.equals("NO")) {
                    post.setModeratorStatus(ModeratorStatus.DECLINED);
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
