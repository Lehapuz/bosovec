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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getUsers")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<User> users = new ArrayList<>();
        try {
            users = Command.getInstance().getUserService().getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (users.size() == 0) {
            session.setAttribute("user", "Пользователи отсутствуют");
        } else {
            session.setAttribute("user", users);
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/listUser.jsp").forward(req, resp);
    }
}
