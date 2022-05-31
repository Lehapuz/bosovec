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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getMyPosts")
public class MyPostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Command.getInstance().getUserService().getAuthoriseUser();
        if (user != null) {
            List<Post> posts = new ArrayList<>();
            List<Post> myPosts = new ArrayList<>();
            try {
                posts = Command.getInstance().getPostService().getAllPosts();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            for (Post post : posts) {
                if (user.getId() == post.getUser().getId()) {
                    myPosts.add(post);
                }
            }
            HttpSession session = req.getSession();
            session.setAttribute("post", myPosts);
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/listMyPost.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
