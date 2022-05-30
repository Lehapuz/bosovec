package by.epam.basavets.servlet.settings;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorizationModerator/setSettings")
public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getModeratorService().getAuthorizeModerator() != null) {
            getServletContext().getRequestDispatcher("/setSettings.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        try {
            Command.getInstance().getSettingsService().setSettings(status);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
    }
}
