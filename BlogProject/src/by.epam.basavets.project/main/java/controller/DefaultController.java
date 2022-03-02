package controller;

import command.Command;
import view.MainMenu;
import view.ModeratorMenu;
import view.UserMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class DefaultController implements Serializable {

    Command command = new Command();
    ModeratorMenu moderatorMenu = new ModeratorMenu(command);
    UserMenu userMenu = new UserMenu(command);
    MainMenu menu = new MainMenu(command);
    private static final long serialVersionUID = 21L;



    public void run() {
        while (true) {
//            try {
//                command.readCollections();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            command.readFromFile();

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

            command.runWriteFile();


//            try {
//                command.writeCollections();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }


        }
    }
}
