package by.epam.basavets.servlet.post;

import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Post;
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
import java.util.stream.Collectors;


@WebServlet("/getPosts")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Post> posts = new ArrayList<>();
        List<Post> correctPosts;
        try {
            posts = Command.getInstance().getPostService().getAllPosts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        correctPosts = posts.stream().filter(post -> post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED))
                .collect(Collectors.toList());
        if (correctPosts.size() == 0) {
            session.setAttribute("post", "Посты отсутствуют");
        } else {
            session.setAttribute("post", correctPosts);
        }
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/listPost.jsp").forward(req, resp);
    }
}
