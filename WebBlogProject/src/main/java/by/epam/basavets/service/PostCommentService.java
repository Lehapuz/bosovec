package by.epam.basavets.service;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.impl.PostCommentDAO;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


public class PostCommentService {

    private final PostCommentDAO postCommentDAO;
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostCommentService(PostCommentDAO postCommentDAO, PostDAO postDAO, UserDAO userDAO) {
        this.postCommentDAO = postCommentDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }


    public void addPostComment(String title, String email, String textComment) {
        try {
            PostComment postComment = new PostComment();
            Post post = postDAO.findPostByTitle(title);
            User user = userDAO.findUserByEmail(email);
            postComment.setText(textComment);
            postComment.setTime(LocalDateTime.now());
            postComment.setPost(post);
            postComment.setUser(user);
            postCommentDAO.addPostComment(postComment);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    public List<PostComment> getAllPostComments() throws SQLException {
        return postCommentDAO.read();
    }


    public void deletePostComment(String textComment, String email) {
        try {
            PostComment postComment = postCommentDAO.findCommentByText(textComment);
            int id = postComment.getUser().getId();
            User user = userDAO.findUserById(id);
            User currentUser = userDAO.findUserByEmail(email);
            if (currentUser.getId() == user.getId()) {
                postCommentDAO.deletePostComment(postComment);
                logger.info("Comments deleted");
            } else {
                logger.info("You are not author");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    public PostComment getPostCommentByText(String textComment) throws SQLException {
        return postCommentDAO.findCommentByText(textComment);
    }


    public void updatePostCommentByText(String textComment, String email, String newTextComment) {
        try {
            PostComment postComment = postCommentDAO.findCommentByText(textComment);
            int id = postComment.getUser().getId();
            User currentUser = userDAO.findUserByEmail(email);
            User user = userDAO.findUserById(id);
            if (currentUser.getId() == user.getId()) {
                postComment.setId(postComment.getId());
                postComment.setText(newTextComment);
                postCommentDAO.updatePostComment(postComment);
                logger.info("Comments updated");
            } else {
                logger.info("You are not author");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
