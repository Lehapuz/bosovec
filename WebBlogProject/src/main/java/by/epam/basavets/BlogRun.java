package by.epam.basavets;

import by.epam.basavets.bean.User;
import by.epam.basavets.controller.DefaultController;
import by.epam.basavets.dao.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

public class BlogRun {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DefaultController defaultController = new DefaultController();
        defaultController.read();
        defaultController.run();







    }
}



