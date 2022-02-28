package command;

import dao.*;
import service.*;

public class Command {

    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();
    ModeratorDAO moderatorDAO = new ModeratorDAO();
    PostCommentDAO postCommentDAO = new PostCommentDAO();
    PostVoteDAO postVoteDAO = new PostVoteDAO();
    SettingsDAO settingsDAO = new SettingsDAO();

    UserService userService = new UserService(userDAO);
    ModeratorService moderatorService = new ModeratorService(moderatorDAO, postDAO);
    PostServise postService = new PostServise(postDAO, userDAO, settingsDAO);
    PostCommentServise postCommentService = new PostCommentServise(postDAO, postCommentDAO);
    PostVoteServise postVoteService = new PostVoteServise(postDAO, postVoteDAO);
    SettingsService settingsService = new SettingsService(settingsDAO);

    public UserService getUserService() {
        return userService;
    }

    public ModeratorService getModeratorService() {
        return moderatorService;
    }

    public PostServise getPostService() {
        return postService;
    }

    public PostCommentServise getPostCommentService() {
        return postCommentService;
    }

    public PostVoteServise getPostVoteService() {
        return postVoteService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }
}






