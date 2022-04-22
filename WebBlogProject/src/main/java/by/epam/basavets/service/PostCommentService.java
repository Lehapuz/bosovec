package by.epam.basavets.service;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.PostCommentDAO;
import by.epam.basavets.dao.PostDAO;
import by.epam.basavets.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDateTime;


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
            logger.error("Такого поста не существует");
        }
    }


    public void getAllPostComments() throws SQLException {
        postCommentDAO.read();
    }


    public void deletePostComment(String textComment, String email) {
        try {
            PostComment postComment = postCommentDAO.findCommentByText(textComment);
            int id = postComment.getUser().getId();
            User user = postCommentDAO.findUserById(id);
            User currentUser = userDAO.findUserByEmail(email);
            if (currentUser.getId() == user.getId()) {
                postCommentDAO.deletePostComment(postComment);
                logger.info("Комментарий успешно удален");
            } else {
                logger.info("Вы не являетесь автором поста");
            }

        } catch (Exception e) {
            logger.error("Такого комментария не существует");
        }
    }


    public void updatePostCommentByText(String textComment, String email, String newTextComment) {
        try {
            PostComment postComment = postCommentDAO.findCommentByText(textComment);
            int id = postComment.getUser().getId();
            User user = postCommentDAO.findUserById(id);
            User currentUser = userDAO.findUserByEmail(email);
            if (currentUser.getId() == user.getId()) {
                postComment.setId(postComment.getId());
                postComment.setText(newTextComment);
                postCommentDAO.updatePostComment(postComment);
                logger.info("Комментарий успешно обновлен");
            } else {
                logger.info("Вы не являетесь автором комментария");
            }
        } catch (Exception e) {
            logger.error("Такого комментария не существует");
        }
    }
}
