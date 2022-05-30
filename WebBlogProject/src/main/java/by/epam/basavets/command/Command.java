package by.epam.basavets.command;

import by.epam.basavets.dao.*;
import by.epam.basavets.service.*;

public class Command {

    private final UserDAO userDAO = new UserDAO();
    private final PostDAO postDAO = new PostDAO();
    private final ModeratorDAO moderatorDAO = new ModeratorDAO();
    private final PostCommentDAO postCommentDAO = new PostCommentDAO();
    private final PostVoteDAO postVoteDAO = new PostVoteDAO();
    private final SettingsDAO settingsDAO = new SettingsDAO();

    private final UserService userService = new UserService(userDAO);
    private final ModeratorService moderatorService = new ModeratorService(moderatorDAO, postDAO);
    private final PostService postService = new PostService(postDAO, userDAO, settingsDAO);
    private final PostCommentService postCommentService = new PostCommentService(postCommentDAO, postDAO, userDAO);
    private final PostVoteService postVoteService = new PostVoteService(postVoteDAO, postDAO);
    private final SettingsService settingsService = new SettingsService(settingsDAO);

    private static Command instance;

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

    public synchronized static Command getInstance() {
        if (instance == null) {
            instance = new Command();
        }
        return instance;
    }

}










