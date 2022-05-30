package by.epam.basavets.servlet.user;

import by.epam.basavets.bean.User;
import by.epam.basavets.command.Command;
import by.epam.basavets.service.UserService;

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
        if (Command.getInstance().getUserService().getAuthoriseUser() != null) {
            getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = Command.getInstance().getUserService().getAuthoriseUser();
        try {
            if (Command.getInstance().getUserService().findUserByEmail(email) == null ||
                    !user.getEmail().equals(Command.getInstance().getUserService().findUserByEmail(email).getEmail())) {
                session.setAttribute("delete", "Неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            }
            else if (!user.getPassword().equals(password)) {
                session.setAttribute("delete", "Неверный пароль");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            } else {
                session.setAttribute("delete", "Аккаунт пользователя успешно удален");
                Command.getInstance().getUserService().deleteUserByEmail(email, password);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
