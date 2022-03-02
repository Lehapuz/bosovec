package dao;

public class DataSource {

    private final ModeratorDAO moderatorDAO;
    private final PostVoteDAO postVoteDAO;
    private final PostCommentDAO postCommentDAO;
    private final PostDAO postDAO;
    private final SettingsDAO settingsDAO;
    private final UserDAO userDAO;


    public DataSource(ModeratorDAO moderatorDAO, PostVoteDAO postVoteDAO,
                      PostCommentDAO postCommentDAO, PostDAO postDAO,
                      SettingsDAO settingsDAO, UserDAO userDAO) {
        this.moderatorDAO = moderatorDAO;
        this.postVoteDAO = postVoteDAO;
        this.postCommentDAO = postCommentDAO;
        this.postDAO = postDAO;
        this.settingsDAO = settingsDAO;
        this.userDAO = userDAO;
    }


    public ModeratorDAO getModeratorDAO() {
        return moderatorDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public PostCommentDAO getPostCommentDAO() {
        return postCommentDAO;
    }

    public PostVoteDAO getPostVoteDAO() {
        return postVoteDAO;
    }

    public SettingsDAO getSettingsDAO() {
        return settingsDAO;
    }

}
