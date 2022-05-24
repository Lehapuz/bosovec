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

@WebServlet("/authorizationUser")
public class UserAuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new Command();
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        command.getUserService().authorizationUser(email, password);
        if (command.getUserService().getAuthorizated()) {
            session.setAttribute("authorization", "Авторизация прошла успешно");
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
        } else {
            session.setAttribute("authorization", "Авторизация не удалась");
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
        }
    }
}
