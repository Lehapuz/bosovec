package by.epam.basavets.servlet.postComment;

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

@WebServlet("/addComment")
public class PostCommentAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getUserService().getAuthoriseUser() != null) {
            getServletContext().getRequestDispatcher("/addComment.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String email = req.getParameter("email");
        String textComment = req.getParameter("textComment");
        HttpSession session = req.getSession();
        try {
            User user = Command.getInstance().getUserService().findUserByEmail(email);
            User authorizeUser = Command.getInstance().getUserService().getAuthoriseUser();
            Post post = Command.getInstance().getPostService().getPostByTitle(title);
            if (user == null || !authorizeUser.getEmail().equals(user.getEmail()) || post == null) {
                session.setAttribute("correct", "Введенные данные не корректны");
            } else {
                Command.getInstance().getPostCommentService().addPostComment(title, email, textComment);
                session.setAttribute("correct", "Комментарий успешно добавлен");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/addComment.jsp").forward(req, resp);
    }
}
