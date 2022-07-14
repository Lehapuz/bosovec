package by.epam.basavets.dao;

import by.epam.basavets.dao.impl.PostCommentDAO;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.PostVoteDAO;
import by.epam.basavets.dao.impl.SettingsDAO;
import by.epam.basavets.dao.impl.*;
import by.epam.basavets.service.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DAOFactory {

    private final UserDAO userDAO = new UserDAO();
    private final PostDAO postDAO = new PostDAO();
    private final PostCommentDAO postCommentDAO = new PostCommentDAO();
    private final PostVoteDAO postVoteDAO = new PostVoteDAO();
    private final SettingsDAO settingsDAO = new SettingsDAO();

    private static DAOFactory instance;

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

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
}










