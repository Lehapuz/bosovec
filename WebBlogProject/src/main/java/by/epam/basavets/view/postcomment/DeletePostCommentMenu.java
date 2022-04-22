package by.epam.basavets.view.postcomment;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class DeletePostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public DeletePostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() throws SQLException {
        String input;
        String input1;
        logger.info("Введите текст комментария который хотите удалить");
        input = scanner.nextLine();
        logger.info("Введите адрес электронной почты");
        input1 = scanner.nextLine();
        command.getPostCommentService().deletePostComment(input, input1);
    }
}
