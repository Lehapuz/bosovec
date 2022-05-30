package by.epam.basavets.servlet.moderator;

import by.epam.basavets.bean.Moderator;
import by.epam.basavets.command.Command;
import by.epam.basavets.service.ModeratorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorizationModerator/deleteModerator")
public class ModeratorDeleteServlet extends HttpServlet {
    Command command = new Command();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (command.getModeratorService().getAuthorizeModerator() != null) {
            getServletContext().getRequestDispatcher("/deleteModerator.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Moderator moderator = Command.getInstance().getModeratorService().getAuthorizeModerator();
        try {
            if (Command.getInstance().getModeratorService().findModeratorByEmail(email) == null ||
                    !moderator.getEmail().equals(Command.getInstance().getModeratorService()
                            .findModeratorByEmail(email).getEmail())) {
                session.setAttribute("delete", "Неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteModerator.jsp").forward(req, resp);
            } else if (!moderator.getPassword().equals(password)) {
                session.setAttribute("delete", "Неверный пароль");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            } else {
                session.setAttribute("delete", "Аккаунт пользователя успешно удален");
                command.getModeratorService().deleteModeratorByEmail(email, password);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
