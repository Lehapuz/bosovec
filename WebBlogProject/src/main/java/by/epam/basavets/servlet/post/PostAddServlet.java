package by.epam.basavets.servlet.post;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addPost")
public class PostAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getUserService().getAuthoriseUser() != null) {
            getServletContext().getRequestDispatcher("/addPost.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        HttpSession session = req.getSession();
        try {
            if (Command.getInstance().getUserService().findUserByEmail(email) == null ||
                    !Command.getInstance().getUserService().getAuthoriseUser().getEmail()
                            .equals(Command.getInstance().getUserService().findUserByEmail(email).getEmail())) {
                session.setAttribute("correctEmail", "Адрес электронной почты указан неверно");
            } else {
                Command.getInstance().getPostService().addNewPost(email, title, text);
                session.setAttribute("correctEmail", "Пост успешно добавлен");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/addPost.jsp").forward(req, resp);
    }
}
