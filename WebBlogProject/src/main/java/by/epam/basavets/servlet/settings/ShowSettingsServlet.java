package by.epam.basavets.servlet.settings;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorizationModerator/showSettings")
public class ShowSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        HttpSession session = req.getSession();
        try {
            session.setAttribute("settings", command.getSettingsService().showSettings());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/showSettings.jsp").forward(req, resp);
    }
}
