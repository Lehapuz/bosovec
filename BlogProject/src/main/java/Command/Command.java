package Command;

import Controller.DefaultController;
import View.MainMenu;
import View.ModeratorMenu;
import View.UserMenu;

public class Command {

    DefaultController defaultController = new DefaultController();
    ModeratorMenu moderatorMenu = new ModeratorMenu(defaultController);
    UserMenu userMenu = new UserMenu(defaultController);
    MainMenu menu = new MainMenu(defaultController);


    public void run() {
        while (true) {
            if (!defaultController.getUserService().getRegistration() &&
            !defaultController.getModeratorService().getRegistration()){
                menu.run();
            }
            if (defaultController.getModeratorService().getRegistration()) {
                moderatorMenu.run();
            }
            if (defaultController.getUserService().getRegistration()) {
                userMenu.run();
            }
        }
    }
}
