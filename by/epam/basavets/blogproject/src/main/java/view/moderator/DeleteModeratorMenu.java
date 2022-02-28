package View.moderator;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DeleteModeratorMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public DeleteModeratorMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        logger.info("Введите адресс электронной почты");
        input = scanner.nextLine();
        logger.info("Введите пароль");
        input1 = scanner.nextLine();
        command.getModeratorService().deleteModeratorByEmail(input, input1);
    }
}
