package by.epam.basavets.servlet.user;

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

@WebServlet("/registrationUser")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registrationUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        User user = null;
        try {
            user = Command.getInstance().getUserService().findUserByEmail(email);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (name.length() == 0) {
            session.setAttribute("registration", "Имя должно быть указано");
        }
        else if (password.length() < 6) {
            session.setAttribute("registration", "Пароль слишком короткий");
        }
        else if (user != null) {
            session.setAttribute("registration", "Пользователь с таким адресом электронной почты уже зарегистрирован");
        }
        else {
            session.setAttribute("registration", "Пользователь успешно зарегистрирован");
            try {
                Command.getInstance().getUserService().registerUser(name, password, email);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/registrationUser.jsp").forward(req, resp);
    }
}
