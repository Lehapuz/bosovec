package by.epam.basavets.servlet.postComment;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
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

@WebServlet("/deleteMyComment")
public class PostCommentDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getUserService().getAuthoriseUser() != null) {
            getServletContext().getRequestDispatcher("/deleteComment.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String textComment = req.getParameter("textComment");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        User authoriseUser = Command.getInstance().getUserService().getAuthoriseUser();
        try {
            User user = Command.getInstance().getUserService().findUserByEmail(email);
            PostComment postComment = Command.getInstance().getPostCommentService().getPostCommentByText(textComment);
            if (postComment == null || authoriseUser.getId() != postComment.getUser().getId()) {
                session.setAttribute("delete", "Введен неверный комментарий");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteComment.jsp").forward(req, resp);
            }
            if (user == null || authoriseUser.getId() != user.getId()) {
                session.setAttribute("delete", "Введен неверный адрес электронной почты");
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/deleteComment.jsp").forward(req, resp);
            } else {
                session.setAttribute("delete", "Комментарий пользователя успешно обновлен");
                Command.getInstance().getPostCommentService().deletePostComment(textComment, email);
                resp.setCharacterEncoding("UTF-8");
                getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
