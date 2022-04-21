package by.epam.basavets.view.postcomment;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AddPostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public AddPostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        logger.info("Выберите пост по названию");
        input = scanner.nextLine();
        logger.info("Добавьте комментарий");
        input1 = scanner.nextLine();
        command.getPostCommentService().addPostComment(input, input1);
    }
}
