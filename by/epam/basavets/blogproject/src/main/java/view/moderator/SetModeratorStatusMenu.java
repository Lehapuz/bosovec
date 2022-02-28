package View.moderator;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SetModeratorStatusMenu {
    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public SetModeratorStatusMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        logger.info("Введите название поста");
        input = scanner.nextLine();
        logger.info("Задайте статус посту");
        input1 = scanner.nextLine();
        command.getModeratorService().setModeratorStatus(input, input1);
    }
}
