package View;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ModeratorMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public ModeratorMenu(Command command) {

        this.command = command;
    }

    public void run() {
        try {
            logger.info("Список доступных команд для Модератора:");

            logger.info("Смена аккаунта модератора: нажмите 1");
            logger.info("Удаление аккаунт модератора: нажмите 2");
            logger.info("Установка настройки для сайта: нажмите 3");
            logger.info("Модерировать новый пост: нажмите 4");
            logger.info("Просмотр всех постов: нажмите 5");
            logger.info("Просмотр комментариев к постам: нажмите 6");
            logger.info("Показать настройки: нажмите 7");
            logger.info("Выход: нажмите 8");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    command.getModeratorService().updateModeratorByEmail();
                    break;
                case "2":
                    command.getModeratorService().deleteModeratorByEmail();
                    break;
                case "3":
                    command.getSettingsService().setSettings();
                    break;
                case "4":
                    command.getModeratorService().setModeratorStatus();
                    break;
                case "5":
                    command.getPostService().getAllPosts();
                    break;
                case "6":
                    command.getPostCommentService().getAllPostComments();
                    break;
                case "7":
                    command.getSettingsService().showSettings();
                    break;
                case "8":
                    command.getModeratorService().exit();
                    break;
                default:
                    logger.error("Неверная команда ввода: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
