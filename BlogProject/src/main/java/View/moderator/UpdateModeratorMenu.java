package View.user;

import Command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UpdateUserMenu {

    Scanner scanner = new Scanner(System.in);
    private final Command command;
    private final Logger logger = LogManager.getRootLogger();

    public UpdateUserMenu(Command command) {
        this.command = command;
    }

    public void show(){
        String input;
        String input1;
        String input2;
        String input3;
        logger.info("Введите адресс электронной почты");
        input = scanner.nextLine();
        logger.info("Введите пароль");
        input1 = scanner.nextLine();
        logger.info("Введите новое имя");
        input2 = scanner.nextLine();
        logger.info("Имя обновлено" + "\n" + "Введите новый пароль");
        input3 = scanner.nextLine();
        logger.info("пароль обновлен");
        command.getUserService().updateUserByEmail(input, input1, input2, input3);
    }
}
