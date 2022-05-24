package by.epam.basavets.servlet.moderator;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registrationModerator")
public class ModeratorAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registrationModerator.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String secretCode = req.getParameter("secretCode");
        String secretCode1 = "Moderator";

        HttpSession session = req.getSession();
        try {
            if (!secretCode1.equals(secretCode)){
                session.setAttribute("registration", "Секретное имя указано не верно");
            }
            if (name.length() == 0){
                session.setAttribute("registration", "Имя должно быть указано");
            }
            if (password.length() < 6){
                session.setAttribute("registration", "Пароль слишком короткий");
            }
            if (command.getModeratorService().findModeratorByEmail(email) != null){
                session.setAttribute("registration", "Модератор с таким адресом электронной почты уже зарегистрирован");
            }
            if (secretCode1.equals(secretCode) && name.length() > 0 && password.length() >= 6
                    && command.getModeratorService().findModeratorByEmail(email) == null){
                session.setAttribute("registration", "Модератор успешно зарегистрирован");
                command.getModeratorService().registrationModerator(name, password, email);
            }
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/registrationModerator.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
