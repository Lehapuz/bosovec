package by.epam.basavets.servlet;

import by.epam.basavets.command.CommandProvider;
import by.epam.basavets.command.CommandProviderException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private final String COMMAND = "command";
    private String command;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        command = req.getParameter(COMMAND);
        if (command == null) {
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            switch (command) {
                case "1" -> req.getServletContext().getRequestDispatcher("/lang.jsp").forward(req, resp);
                case "2" -> CommandProvider.getInstance().getUser(req, resp);
                case "3" -> CommandProvider.getInstance().getPosts(req, resp);
                case "4" -> CommandProvider.getInstance().getMyPosts(req, resp);
                case "5" -> req.getServletContext().getRequestDispatcher("/exitUser.jsp").forward(req, resp);
                case "6" -> CommandProvider.getInstance().getSettings(req, resp);
                case "7" -> CommandProvider.getInstance().getPostById(req, resp);
                case "8" -> CommandProvider.getInstance().getMyComments(req, resp);
                default -> req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (command == null) {
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                switch (command) {
                    case "1" -> CommandProvider.getInstance().changeLanguage(req, resp);
                    case "2" -> CommandProvider.getInstance().actionUser(req, resp);
                    case "4" -> CommandProvider.getInstance().actionMyPosts(req, resp);
                    case "5" -> CommandProvider.getInstance().exitUser(req, resp);
                    case "6" -> CommandProvider.getInstance().setSettings(req, resp);
                    case "7" -> CommandProvider.getInstance().actionPostById(req, resp);
                    case "8" -> CommandProvider.getInstance().actionMyComments(req, resp);
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e.getMessage());
        }
    }
}