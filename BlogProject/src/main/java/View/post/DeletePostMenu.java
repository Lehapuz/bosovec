package View.post;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddPostMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public AddPostMenu(Command command) {
        this.command = command;
    }

    public void show(){
        String input;
        String input1;
        String input2;
        logger.info("Введите адрес электронной почты");
        input = scanner.nextLine();
        logger.info("Введите заголовок");
        input1 = scanner.nextLine();
        logger.info("Введите текст");
        input2 = scanner.nextLine();
        command.getPostService().addNewPost(input, input1, input2);
    }
}
