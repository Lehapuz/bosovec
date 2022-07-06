package by.epam.basavets.view.postvote;

import by.epam.basavets.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class SetPostVoteMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public SetPostVoteMenu(Command command) {
        this.command = command;
    }

    public void show() throws SQLException {
        String input;
        String input1;
        logger.info("Проголосуйте за пост");
        input = scanner.nextLine();
        logger.info("Оцените пост");
        input1 = scanner.nextLine();
        command.getPostVoteService().setPostVote(input, input1);
    }
}
