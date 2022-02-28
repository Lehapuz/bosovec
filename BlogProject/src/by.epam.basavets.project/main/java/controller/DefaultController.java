package controller;

import command.Command;
import view.MainMenu;
import view.ModeratorMenu;
import view.UserMenu;

public class DefaultController {

    Command command = new Command();
    ModeratorMenu moderatorMenu = new ModeratorMenu(command);
    UserMenu userMenu = new UserMenu(command);
    MainMenu menu = new MainMenu(command);


    public void run() {
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
