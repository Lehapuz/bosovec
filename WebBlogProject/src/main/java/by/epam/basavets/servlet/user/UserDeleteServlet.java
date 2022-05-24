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

@WebServlet("/authorizationUser/deleteUser")
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        try {
            if (command.getUserService().findUserByEmail(email) == null) {
                session.setAttribute("delete", "Неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            }
            if (!command.getUserService().findUserByEmail(email).getPassword().equals(password)) {
                session.setAttribute("delete", "Неверный пароль");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            }
            if (command.getUserService().findUserByEmail(email) != null && command
                    .getUserService().findUserByEmail(email).getPassword().equals(password)) {
                session.setAttribute("delete", "Аккаунт пользователя успешно обновлен");
                command.getUserService().deleteUserByEmail(email, password);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
