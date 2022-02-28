package View.post;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DeletePostMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public DeletePostMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        logger.info("Введите название который хотите удалить");
        input = scanner.nextLine();
        command.getPostService().deletePostByTitle(input);
    }
}
