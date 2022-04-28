package by.epam.basavets.controller;

import by.epam.basavets.command.Command;
import by.epam.basavets.view.MainMenu;
import by.epam.basavets.view.ModeratorMenu;
import by.epam.basavets.view.UserMenu;

import java.sql.SQLException;

public class DefaultController {

    Command command = new Command();
    ModeratorMenu moderatorMenu = new ModeratorMenu(command);
    UserMenu userMenu = new UserMenu(command);
    MainMenu menu = new MainMenu(command);


    public void run() throws SQLException {
        while (true) {
            if (!command.getUserService().getAuthorithated() &&
                    !command.getModeratorService().getAuthorithated()) {
                menu.run();
            }
            if (command.getModeratorService().getAuthorithated()) {
                moderatorMenu.run();
            }
            if (command.getUserService().getAuthorithated()) {
                userMenu.run();
            }
        }
    }
}
