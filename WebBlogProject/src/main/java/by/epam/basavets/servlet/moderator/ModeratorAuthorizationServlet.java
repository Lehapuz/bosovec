package by.epam.basavets.servlet.moderator;

import by.epam.basavets.command.Command;
import by.epam.basavets.service.ModeratorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/authorizationModerator")
public class ModeratorAuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/authorizationModerator.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        Command.getInstance().getModeratorService().authorizationModerator(email, password);
        if (Command.getInstance().getModeratorService().getAuthorizeModerator() != null) {
            session.setAttribute("authorization", "Авторизация прошла успешно");
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
        } else {
            session.setAttribute("authorization", "Авторизация не удалась");
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/authorizationModerator.jsp").forward(req, resp);
        }
    }
}
