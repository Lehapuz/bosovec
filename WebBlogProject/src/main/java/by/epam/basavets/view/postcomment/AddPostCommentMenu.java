package by.epam.basavets.view.postcomment;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class AddPostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public AddPostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() throws SQLException {
        String input;
        String input1;
        String input2;
        logger.info("Выберите пост по названию");
        input = scanner.nextLine();
        logger.info("Введите адрес электронной почты");
        input1 = scanner.nextLine();
        logger.info("Добавьте комментарий");
        input2 = scanner.nextLine();
        command.getPostCommentService().addPostComment(input, input1, input2);
    }
}
