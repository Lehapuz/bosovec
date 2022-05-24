package by.epam.basavets.servlet.moderator;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/getModerators")
public class ModeratorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Command command = new Command();
        HttpSession session = req.getSession();
        try {
            if (command.getModeratorService().getAllModerators().size() == 0) {
                session.setAttribute("moderator", "Модераторы отсутствуют");
            } else {
                session.setAttribute("moderator", command.getModeratorService().getAllModerators());
            }
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/listModerator.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
