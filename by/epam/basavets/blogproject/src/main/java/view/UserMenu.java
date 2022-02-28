package View;

import Command.Command;
import View.post.AddPostMenu;
import View.post.DeletePostMenu;
import View.post.UpdatePostMenu;
import View.postcomment.AddPostCommentMenu;
import View.postcomment.DeletePostCommentMenu;
import View.postcomment.UpdatePostCommentMenu;
import View.user.DeleteUserMenu;
import View.user.UpdateUserMenu;
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
                    UpdateUserMenu updateUserMenu = new UpdateUserMenu(command);
                    updateUserMenu.show();
                    break;
                case "2":
                    DeleteUserMenu deleteUserMenu = new DeleteUserMenu(command);
                    deleteUserMenu.show();
                    break;
                case "3":
                    AddPostMenu addPostMenu = new AddPostMenu(command);
                    addPostMenu.show();
                    break;
                case "4":
                    command.getPostService().getAllPosts();
                    break;
                case "5":
                    DeletePostMenu deletePostMenu = new DeletePostMenu(command);
                    deletePostMenu.show();
                    break;
                case "6":
                    UpdatePostMenu updatePostMenu = new UpdatePostMenu(command);
                    updatePostMenu.show();
                    break;
                case "7":
                    AddPostCommentMenu addPostCommentMenu = new AddPostCommentMenu(command);
                    addPostCommentMenu.show();
                    break;
                case "8":
                    command.getPostCommentService().getAllPostComments();
                    break;
                case "9":
                    DeletePostCommentMenu deletePostCommentMenu = new DeletePostCommentMenu(command);
                    deletePostCommentMenu.show();
                    break;
                case "10":
                    UpdatePostCommentMenu updatePostCommentMenu = new UpdatePostCommentMenu(command);
                    updatePostCommentMenu.show();
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
