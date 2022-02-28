package View.settings;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SettingsMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public SettingsMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        logger.info("Разрешить или запретить доступ");
        input = scanner.nextLine();
        command.getSettingsService().setSettings(input);
    }
}
