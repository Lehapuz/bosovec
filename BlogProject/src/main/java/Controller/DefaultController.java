package Controller;

import Command.Command;
import View.MainMenu;
import View.ModeratorMenu;
import View.UserMenu;

public class DefaultController {

    Command command = new Command();
    ModeratorMenu moderatorMenu = new ModeratorMenu(command);
    UserMenu userMenu = new UserMenu(command);
    MainMenu menu = new MainMenu(command);


    public void run() {
        while (true) {
            if (!command.getUserService().getRegistration() &&
                    !command.getModeratorService().getRegistration()) {
                menu.run();
            }
            if (command.getModeratorService().getRegistration()) {
                moderatorMenu.run();
            }
            if (command.getUserService().getRegistration()) {
                userMenu.run();
            }
        }
    }
}
