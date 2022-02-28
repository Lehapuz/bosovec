package view.postcomment;

import command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DeletePostCommentMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public DeletePostCommentMenu(Command command) {
        this.command = command;
    }

    public void show() {
        String input;
        logger.info("Введите текст комментария который хотите удалить");
        input = scanner.nextLine();
        command.getPostCommentService().deletePostComment(input);
    }
}
