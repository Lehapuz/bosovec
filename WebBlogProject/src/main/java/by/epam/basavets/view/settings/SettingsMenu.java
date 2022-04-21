package by.epam.basavets.view.settings;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class SettingsMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public SettingsMenu(Command command) {
        this.command = command;
    }

    public void show() throws SQLException {
        String input;
        logger.info("Разрешить или запретить доступ");
        input = scanner.nextLine();
        command.getSettingsService().setSettings(input);
    }
}
