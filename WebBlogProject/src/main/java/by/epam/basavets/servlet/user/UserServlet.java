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


@WebServlet("/getUsers")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Command command = new Command();
        HttpSession session = req.getSession();
        try {
            if (command.getUserService().getAllUsers().size() == 0) {
                session.setAttribute("user", "Пользователи отсутствуют");
            } else {
                session.setAttribute("user", command.getUserService().getAllUsers());
            }
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/listUser.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
