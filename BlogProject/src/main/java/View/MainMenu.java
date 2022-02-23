package View;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public MainMenu(Command command) {
        this.command = command;
    }

    public void run() {
        try {
            logger.info("Список доступных команд для незарегистрированного пользователя:");

            logger.info("Регистрация модератора: нажмите 1");
            logger.info("Просмотр аккаунта модератора: нажмите 2");
            logger.info("Авторизация модератора: нажмите 3");
            logger.info("Регистрация пользователя: нажмите 4");
            logger.info("Просмотр всех пользователей: нажмите 5");
            logger.info("Просмотр всех постов: нажмите 6");
            logger.info("Проголосовать за пост: нажмите 7");
            logger.info("Авторизация: нажмите 8");
            logger.info("Просмотр комментариев к постам: нажмите 9");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    command.getModeratorService().registerModerator();
                    break;
                case "2":
                    command.getModeratorService().getAllModerators();
                    break;
                case "3":
                    command.getModeratorService().authorithationModerator();
                    break;
                case "4":
                    command.getUserService().registerUser();
                    break;
                case "5":
                    command.getUserService().getAllUsers();
                    break;
                case "6":
                    command.getPostService().getAllPosts();
                    break;
                case "7":
                    command.getPostVoteService().setPostVote();
                    break;
                case "8":
                    command.getUserService().authorithationUser();
                    break;
                case "9":
                    command.getPostCommentService().getAllPostComments();

                default:
                    logger.error("Неверная команда ввода: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
