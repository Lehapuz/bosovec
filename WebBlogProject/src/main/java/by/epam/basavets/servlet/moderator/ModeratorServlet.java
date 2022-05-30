package by.epam.basavets.servlet.moderator;

import by.epam.basavets.bean.Moderator;
import by.epam.basavets.bean.User;
import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getModerators")
public class ModeratorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Moderator> moderators = new ArrayList<>();
        try {
            moderators = Command.getInstance().getModeratorService().getAllModerators();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (moderators.size() == 0) {
            session.setAttribute("moderator", "Модераторы отсутствуют");
        } else {
            session.setAttribute("moderator", moderators);
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/listModerator.jsp").forward(req, resp);
    }
}
