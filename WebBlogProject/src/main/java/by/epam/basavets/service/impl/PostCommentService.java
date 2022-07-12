package by.epam.basavets.service.impl;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.impl.PostCommentDAO;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.UserDAO;
import by.epam.basavets.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;


public class PostCommentService implements by.epam.basavets.service.PostCommentService {

    private final PostCommentDAO postCommentDAO;
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostCommentService(PostCommentDAO postCommentDAO, PostDAO postDAO, UserDAO userDAO) {
        this.postCommentDAO = postCommentDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
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
            logger.error("Can not add comment");
            throw new ServiceException("Can not add comment", e);
        }
    }

    @Override
    public List<PostComment> getAllPostComments() {
        return postCommentDAO.read();
    }

    @Override
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
            logger.error("Can not delete comment");
            throw new ServiceException("Can not delete comment", e);
        }
    }

    @Override
    public PostComment getPostCommentByText(String textComment) {
        return postCommentDAO.findCommentByText(textComment);
    }

    @Override
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
            logger.error("Can not update comment");
            throw new ServiceException("Can not update comment", e);
        }
    }
}
