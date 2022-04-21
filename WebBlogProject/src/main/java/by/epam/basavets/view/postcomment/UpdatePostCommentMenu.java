package by.epam.basavets.view.postcomment;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UpdatePostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public UpdatePostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        String input1;
        logger.info("Введите текст комментария который хотите откорректировать");
        input = scanner.nextLine();
        logger.info("Введите комментарий");
        input1 = scanner.nextLine();
        command.getPostCommentService().updatePostCommentByText(input, input1);
    }
}
