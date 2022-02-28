package View.moderator;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class RegistrationModeratorMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public RegistrationModeratorMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        String input2;
        logger.info("Введите имя");
        input = scanner.nextLine();
        logger.info("Имя добавлено" + "\n" + "Введите пароль");
        input1 = scanner.nextLine();
        logger.info("пароль добавлен" + "\n" + "Введите адресс электронной почты");
        input2 = scanner.nextLine();
        command.getModeratorService().registerModerator(input, input1, input2);
    }
}
