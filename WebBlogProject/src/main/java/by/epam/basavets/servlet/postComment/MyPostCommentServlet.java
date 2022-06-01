package by.epam.basavets.servlet.postComment;

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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getMyComments")
public class MyPostCommentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Command.getInstance().getUserService().getAuthoriseUser() == null) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        } else {
            req.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            User user = Command.getInstance().getUserService().getAuthoriseUser();
            List<PostComment> postComments;
            List<PostComment> myPostComments = new ArrayList<>();
            try {
                postComments = Command.getInstance().getPostCommentService().getAllPostComments();
                for (PostComment postComment : postComments) {
                    if (user.getId() == postComment.getUser().getId()) {
                        myPostComments.add(postComment);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            session.setAttribute("postComment", myPostComments);
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/listMyPostComment.jsp").forward(req, resp);
        }
    }
}



