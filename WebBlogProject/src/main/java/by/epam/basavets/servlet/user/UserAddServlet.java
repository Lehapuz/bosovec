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

@WebServlet("/registrationUser")
public class UserAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registrationUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        try {
            if (name.length() == 0){
                session.setAttribute("registration", "Имя должно быть указано");
            }
            if (password.length() < 6){
                session.setAttribute("registration", "Пароль слишком короткий");
            }
            if (command.getUserService().findUserByEmail(email) != null){
                session.setAttribute("registration", "Пользователь с таким адресом электронной почты уже зарегистрирован");
            }
            if (name.length() > 0 && password.length() >= 6 && command.getUserService().findUserByEmail(email) == null){
                session.setAttribute("registration", "Пользователь успешно зарегистрирован");
                command.getUserService().registerUser(name, password, email);
            }
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/registrationUser.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
