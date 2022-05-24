package by.epam.basavets.servlet.user;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/authorizationUser/updateUser")
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String newPassword = req.getParameter("newPassword");
        HttpSession session = req.getSession();
        try {
            if (command.getUserService().findUserByEmail(email) == null) {
                session.setAttribute("update", "Неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            }
            if (!command.getUserService().findUserByEmail(email).getPassword().equals(password)) {
                session.setAttribute("update", "Неверный пароль");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            }
            if (name.length() == 0) {
                session.setAttribute("update", "Имя должно быть указано");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            }
            if (newPassword.length() < 6) {
                session.setAttribute("update", "Новый пароль слишком короткий");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            }
            if (command.getUserService().findUserByEmail(email) != null && command
                    .getUserService().findUserByEmail(email).getPassword().equals(password) &&
                    name.length() != 0 && newPassword.length() >= 6) {
                session.setAttribute("update", "Аккаунт пользователя успешно обновлен");
                command.getUserService().updateUserByEmail(email, password, name, newPassword);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
