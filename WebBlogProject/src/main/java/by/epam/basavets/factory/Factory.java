package by.epam.basavets.factory;

import by.epam.basavets.dao.impl.*;
import by.epam.basavets.service.*;

public class Factory {

    private final UserDAO userDAO = new UserDAO();
    private final PostDAO postDAO = new PostDAO();
    private final PostCommentDAO postCommentDAO = new PostCommentDAO();
    private final PostVoteDAO postVoteDAO = new PostVoteDAO();
    private final SettingsDAO settingsDAO = new SettingsDAO();

    private final UserService userService = new UserService(userDAO, postDAO);
    private final PostService postService = new PostService(postDAO, userDAO, postCommentDAO, settingsDAO);
    private final PostCommentService postCommentService = new PostCommentService(postCommentDAO, postDAO, userDAO);
    private final PostVoteService postVoteService = new PostVoteService(postVoteDAO, postDAO);
    private final SettingsService settingsService = new SettingsService(settingsDAO);

    private static Factory instance;

    public UserService getUserService() {
        return userService;
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

    public UserDAO getUserDAO(){
        return userDAO;
    }

    public PostDAO getPostDAO(){
        return postDAO;
    }

    public synchronized static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
}










