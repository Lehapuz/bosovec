package by.epam.basavets.command;

import by.epam.basavets.bean.ModeratorStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.User;
import by.epam.basavets.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PostCommand {
    private final String MODERATOR = "MODERATOR";
    private final String USER = "USER";
    private final String SIZE = "size";
    private final String POSTS = "posts";
    private final String AUTHORIZE_USER = "authorizeUser";
    private final String COMMAND = "command";


    public void getPosts(HttpServletRequest req, HttpServletResponse resp) {

        String command = req.getParameter(COMMAND);

        if (command.equals("3")){



        HttpSession session = req.getSession();
        List<Post> posts;
        List<Post> correctPosts;
        try {
            posts = ServiceFactory.getInstance().getPostService().getAllPosts();
            correctPosts = posts.stream().filter(post -> post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED))
                    .collect(Collectors.toList());
            if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                int size = correctPosts.size();
                session.setAttribute(SIZE, size);
                session.setAttribute(POSTS, correctPosts);
                req.getServletContext().getRequestDispatcher("/listPost.jsp").forward(req, resp);
            } else {
                User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                switch (user.getRole().getRoleTypes().toString()) {
                    case MODERATOR -> {
                        int size = posts.size();
                        session.setAttribute(SIZE, size);
                        session.setAttribute(POSTS, posts);
                        req.getServletContext().getRequestDispatcher("/listModeratorPost.jsp").forward(req, resp);
                    }
                    case USER -> {
                        int size1 = correctPosts.size();
                        session.setAttribute(SIZE, size1);
                        session.setAttribute(POSTS, correctPosts);
                        req.getServletContext().getRequestDispatcher("/listUserPost.jsp").forward(req, resp);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e.getMessage());
        }
    }
    }
}
