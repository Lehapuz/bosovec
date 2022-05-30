package by.epam.basavets.servlet.moderator;

import by.epam.basavets.bean.Moderator;
import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorizationModerator/updateModerator")
public class ModeratorUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getModeratorService().getAuthorizeModerator() != null) {
            getServletContext().getRequestDispatcher("/updateModerator.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String newPassword = req.getParameter("newPassword");
        HttpSession session = req.getSession();
        Moderator moderator = Command.getInstance().getModeratorService().getAuthorizeModerator();
        try {
            if (Command.getInstance().getModeratorService().findModeratorByEmail(email) == null ||
                    !moderator.getEmail().equals(Command.getInstance().getModeratorService()
                            .findModeratorByEmail(email).getEmail())) {
                session.setAttribute("update", "Неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateModerator.jsp").forward(req, resp);
            } else if (!moderator.getPassword().equals(password)) {
                session.setAttribute("update", "Неверный пароль");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateModerator.jsp").forward(req, resp);
            } else if (name.length() == 0) {
                session.setAttribute("update", "Имя должно быть указано");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateModerator.jsp").forward(req, resp);
            } else if (newPassword.length() < 6) {
                session.setAttribute("update", "Новый пароль слишком короткий");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateModerator.jsp").forward(req, resp);
            } else {
                session.setAttribute("update", "Аккаунт пользователя успешно обновлен");
                Command.getInstance().getModeratorService().updateModeratorByEmail(email, password, name, newPassword);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
