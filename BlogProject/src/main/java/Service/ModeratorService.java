package Service;

import Bean.Enum.ModeratorStatus;
import Bean.Moderator;
import Bean.Post;
import DAO.ModeratorDAO;
import DAO.PostDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ModeratorService {

    private final ModeratorDAO moderatorDAO;
    private final PostDAO postDAO;
    Scanner scanner = new Scanner(System.in);
    private boolean isRegistration;
    private final Logger logger = LogManager.getRootLogger();

    public ModeratorService(ModeratorDAO moderatorDAO, PostDAO postDAO) {
        this.moderatorDAO = moderatorDAO;
        this.postDAO = postDAO;
    }


    public void registerModerator() {
        Moderator moderator = new Moderator();
        logger.info("Введите имя");
        String input = scanner.nextLine();
        int i = 0;
        moderator.setName(input);
        logger.info("Имя добавлено" + "\n" + "Введите пароль");
        input = scanner.nextLine();
        moderator.setPassword(input);
        logger.info("пароль добавлен" + "\n" + "Введите адресс электронной почты");
        input = scanner.nextLine();
        moderator.setEmail(input);
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


    public void deleteModeratorByEmail() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    moderatorDAO.deleteModerator(moderator);
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


    public void updateModeratorByEmail() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            Moderator moderator;
            Moderator moderator1 = new Moderator();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                moderator = moderatorDAO.findModeratorByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    moderator1.setEmail(moderator.getEmail());
                    moderator1.setId(moderator.getId());
                    moderatorDAO.updateModerator(moderator, moderator1);
                    logger.info("Введите новое имя");
                    input = scanner.nextLine();
                    moderator1.setName(input);
                    logger.info("Имя обновлено" + "\n" + "Введите новый пароль");
                    input = scanner.nextLine();
                    moderator1.setPassword(input);
                    logger.info("пароль обновлен");
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


    public void authorithationModerator() {
        try {
            logger.info("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(input);
                logger.info("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    logger.info("Авторизация пользователя прошла успешно");
                }
                System.out.println(moderator.toString());
                isRegistration = true;
            } else {
                logger.error("Авторизация не прошла");
            }
        } catch (Exception e) {
            logger.error("Такого пользователя не существует");
        }
    }


    public void setModeratorStatus() {
        try {
            logger.info("Введите название поста");
            String input = scanner.nextLine();
            Post post;
            if (input.equals(postDAO.findPostByTitle(input).getTitle())) {
                post = postDAO.findPostByTitle(input);
                logger.info("Задайте статус посту");
                input = scanner.nextLine();
                if (input.equals("OK")) {
                    post.setModeratorStatus(ModeratorStatus.ACCEPTED);
                    logger.info(("Статус изменен"));
                }
                if (input.equals("NO")) {
                    post.setModeratorStatus(ModeratorStatus.DECLINED);
                    logger.info(("Статус изменен"));
                }
            }
        } catch (Exception e) {
            logger.error("Пост не найден");
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
