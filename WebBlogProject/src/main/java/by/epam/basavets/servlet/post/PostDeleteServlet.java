package by.epam.basavets.servlet.post;

import by.epam.basavets.bean.Post;
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

@WebServlet("/deleteMyPost")
public class PostDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getUserService().getAuthoriseUser() != null) {
            getServletContext().getRequestDispatcher("/deletePost.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        HttpSession session = req.getSession();
        User user = Command.getInstance().getUserService().getAuthoriseUser();
        try {
            Post post = Command.getInstance().getPostService().getPostByTitle(title);
            if (post == null || user.getId() != post.getUser().getId()) {
                session.setAttribute("delete", "Введено неверное название поста");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deletePost.jsp").forward(req, resp);
            } else {
                session.setAttribute("delete", "Пост пользователя успешно удален");
                Command.getInstance().getPostService().deletePostByTitle(title);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
