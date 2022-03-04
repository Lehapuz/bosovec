package controller;

import command.Command;
import view.MainMenu;
import view.ModeratorMenu;
import view.UserMenu;

import java.io.IOException;

public class DefaultController {

    Command command = new Command();
    ModeratorMenu moderatorMenu = new ModeratorMenu(command);
    UserMenu userMenu = new UserMenu(command);
    MainMenu menu = new MainMenu(command);


    public void read() throws IOException, ClassNotFoundException {
        command.readFromFile();
    }

    public void run() throws IOException {
        while (true) {
            // read from file
//            try {
//                command.readCollections();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

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

// wright in file
//            try {
//                command.writeCollections();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }
    }
}
