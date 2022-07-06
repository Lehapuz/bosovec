package by.epam.basavets.view;

import by.epam.basavets.command.Command;
import by.epam.basavets.view.moderator.DeleteModeratorMenu;
import by.epam.basavets.view.moderator.SetModeratorStatusMenu;
import by.epam.basavets.view.moderator.UpdateModeratorMenu;
import by.epam.basavets.view.settings.SettingsMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ModeratorMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public ModeratorMenu(Command command) {

        this.command = command;
    }

    public void run() throws SQLException {
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
                UpdateModeratorMenu updateModeratorMenu = new UpdateModeratorMenu(command);
                updateModeratorMenu.show();
                break;
            case "2":
                DeleteModeratorMenu deleteModeratorMenu = new DeleteModeratorMenu(command);
                deleteModeratorMenu.show();
                break;
            case "3":
                SettingsMenu settingsMenu = new SettingsMenu(command);
                settingsMenu.show();
                break;
            case "4":
                SetModeratorStatusMenu setModeratorStatusMenu = new SetModeratorStatusMenu(command);
                setModeratorStatusMenu.show();
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
    }
}
