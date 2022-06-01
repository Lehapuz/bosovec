package by.epam.basavets.servlet.postComment;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
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


@WebServlet("/post/getComments")
public class PostCommentServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        HttpSession session = req.getSession();
        List<PostComment> postComments;
        List<PostComment> currentPostComments = new ArrayList<>();
        try {
            Post post = Command.getInstance().getPostService().getPostByTitle(title);
            postComments = Command.getInstance().getPostCommentService().getAllPostComments();
            for (PostComment postComment : postComments) {
                if (post.getId() == postComment.getPost().getId()) {
                    currentPostComments.add(postComment);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        session.setAttribute("postComment", currentPostComments);
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/listCurrentPostComment.jsp").forward(req, resp);
    }
}



