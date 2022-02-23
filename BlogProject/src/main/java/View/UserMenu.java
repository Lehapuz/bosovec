package View;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public UserMenu(Command command) {

        this.command = command;
    }

    public void run() {
        try {
            logger.info("Список доступных команд для зарегистрированного пользователя:");

            logger.info("Обновление аккаунта пользователя: нажмите 1");
            logger.info("Удаление аккаунта пользователя: нажмите 2");
            logger.info("Добавление поста: нажмите 3");
            logger.info("Просмотр всех постов: нажмите 4");
            logger.info("Удалить пост: нажмите 5");
            logger.info("Обновить пост: нажмите 6");
            logger.info("Добавить комментарий: нажмите 7");
            logger.info("Просмотр комментариев к постам: нажмите 8");
            logger.info("Удалить комментарий: нажмите 9");
            logger.info("Обновить комментарий: нажмите 10");
            logger.info("Выход: нажмите 11");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    command.getUserService().updateUserByEmail();
                    break;
                case "2":
                    command.getUserService().deleteUserByEmail();
                    break;
                case "3":
                    command.getPostService().addNewPost();
                    break;
                case "4":
                    command.getPostService().getAllPosts();
                    break;
                case "5":
                    command.getPostService().deletePostByTitle();
                    break;
                case "6":
                    command.getPostService().updatePostByTitle();
                    break;
                case "7":
                    command.getPostCommentService().addPostComment();
                    break;
                case "8":
                    command.getPostCommentService().getAllPostComments();
                    break;
                case "9":
                    command.getPostCommentService().deletePostComment();
                    break;
                case "10":
                    command.getPostCommentService().updatePostCommentByText();
                    break;
                case "11":
                    command.getUserService().exit();
                    break;
                default:
                    logger.error("Неверная команда ввода: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
