package by.epam.basavets.command;

import by.epam.basavets.dao.*;
import by.epam.basavets.service.*;

import java.io.Serializable;

public class Command implements Serializable {

    private static final long serialVersionUID = 11L;

    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();
    ModeratorDAO moderatorDAO = new ModeratorDAO();
    PostCommentDAO postCommentDAO = new PostCommentDAO();
    PostVoteDAO postVoteDAO = new PostVoteDAO();
    SettingsDAO settingsDAO = new SettingsDAO();

    UserService userService = new UserService(userDAO);
    ModeratorService moderatorService = new ModeratorService(moderatorDAO, postDAO);
    PostService postService = new PostService(postDAO, userDAO, settingsDAO);
    PostCommentService postCommentService = new PostCommentService(postCommentDAO, postDAO, userDAO);
    PostVoteService postVoteService = new PostVoteService(postVoteDAO, postDAO);
    SettingsService settingsService = new SettingsService(settingsDAO);


    public UserService getUserService() {
        return userService;
    }

    public ModeratorService getModeratorService() {
        return moderatorService;
    }

    public PostService getPostService() {
        return postService;
    }

    public PostCommentService getPostCommentService() {
        return postCommentService;
    }

    public PostVoteService getPostVoteService() {
        return postVoteService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }
}










