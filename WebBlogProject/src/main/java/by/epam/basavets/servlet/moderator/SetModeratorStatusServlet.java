package by.epam.basavets.servlet.moderator;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setModeratorStatus")
public class SetModeratorStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getModeratorService().getAuthorizeModerator() != null) {
            getServletContext().getRequestDispatcher("/setModeratorStatus.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String postTitle = req.getParameter("postTitle");
        String status = req.getParameter("status");
        Command.getInstance().getModeratorService().setModeratorStatus(postTitle, status);
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
    }
}
