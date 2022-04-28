package by.epam.basavets;

import by.epam.basavets.controller.DefaultController;

import java.sql.SQLException;

public class BlogRun {
    public static void main(String[] args) throws SQLException {
        DefaultController defaultController = new DefaultController();
        defaultController.run();
    }
}



