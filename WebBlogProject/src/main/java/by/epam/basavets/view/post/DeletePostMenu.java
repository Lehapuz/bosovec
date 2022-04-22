package by.epam.basavets.view.post;

import by.epam.basavets.command.Command;
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
        String input1;
        logger.info("Введите название который хотите удалить");
        input = scanner.nextLine();
        logger.info("Введите свой адрес электронной почты");
        input1 = scanner.nextLine();
        command.getPostService().deletePostByTitle(input, input1);
    }
}
