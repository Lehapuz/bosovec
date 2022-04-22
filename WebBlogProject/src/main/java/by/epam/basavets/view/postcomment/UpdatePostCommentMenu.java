package by.epam.basavets.view.postcomment;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdatePostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public UpdatePostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() throws SQLException {
        String input;
        String input1;
        String input2;
        logger.info("Введите текст комментария который хотите откорректировать");
        input = scanner.nextLine();
        logger.info("Введите адрес электронной почты");
        input1 = scanner.nextLine();
        logger.info("Введите новый комментарий");
        input2 = scanner.nextLine();
        command.getPostCommentService().updatePostCommentByText(input, input1, input2);
    }
}
