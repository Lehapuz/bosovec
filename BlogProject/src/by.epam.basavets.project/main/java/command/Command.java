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
    DataSource dataSource = new DataSource(moderatorDAO, postVoteDAO, postCommentDAO, postDAO, settingsDAO, userDAO);

    UserService userService = new UserService(dataSource);
    ModeratorService moderatorService = new ModeratorService(dataSource);
    PostServise postService = new PostServise(dataSource);
    PostCommentServise postCommentService = new PostCommentServise(dataSource);
    PostVoteServise postVoteService = new PostVoteServise(dataSource);
    SettingsService settingsService = new SettingsService(dataSource);

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






