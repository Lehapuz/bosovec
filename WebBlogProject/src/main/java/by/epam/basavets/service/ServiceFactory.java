package by.epam.basavets.service;

import by.epam.basavets.dao.DAOFactory;
import by.epam.basavets.dao.impl.*;

public class ServiceFactory {

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final PostDAO postDAO = DAOFactory.getInstance().getPostDAO();
    private final PostCommentDAO postCommentDAO = DAOFactory.getInstance().getPostCommentDAO();
    private final PostVoteDAO postVoteDAO = DAOFactory.getInstance().getPostVoteDAO();
    private final SettingsDAO settingsDAO = DAOFactory.getInstance().getSettingsDAO();

    private final UserService userService = new by.epam.basavets.service.impl.UserService(userDAO, postDAO);
    private final PostService postService = new by.epam.basavets.service.impl
            .PostService(postDAO, userDAO, postCommentDAO, postVoteDAO, settingsDAO);
    private final PostCommentService postCommentService = new by.epam.basavets.service.impl
            .PostCommentService(postCommentDAO, postDAO, userDAO);
    private final PostVoteService postVoteService = new by.epam.basavets.service.impl
            .PostVoteService(postVoteDAO, postDAO);
    private final SettingsService settingsService = new by.epam.basavets.service.impl.SettingsService(settingsDAO);

    private static ServiceFactory instance;

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

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }
}










