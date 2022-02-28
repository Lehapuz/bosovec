package View.post;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UpdatePostMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public UpdatePostMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        String input2;
        logger.info("Введите название  который хотите откорректировать");
        input = scanner.nextLine();
        logger.info("Введите новое название");
        input1 = scanner.nextLine();
        logger.info("Введите новый текст");
        input2 = scanner.nextLine();
        command.getPostService().updatePostByTitle(input, input1, input2);
    }
}
