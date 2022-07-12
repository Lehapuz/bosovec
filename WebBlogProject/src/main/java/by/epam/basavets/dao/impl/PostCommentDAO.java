package by.epam.basavets.dao.impl;

import by.epam.basavets.bean.PostComment;
import by.epam.basavets.dao.ConnectionPool;
import by.epam.basavets.dao.DAOException;
import by.epam.basavets.dao.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostCommentDAO implements by.epam.basavets.dao.PostCommentDAO {

    private final Logger logger = LogManager.getRootLogger();


    @Override
    public void addPostComment(PostComment postComment) {
        try {
            String sql = "INSERT INTO post_comments (text, time, post_id, user_id) " +
                    "VALUES (?, ?, ?, ?)";
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, postComment.getText());
            statement.setString(2, postComment.getTime().toString());
            statement.setInt(3, postComment.getPost().getId());
            statement.setInt(4, postComment.getUser().getId());
            statement.execute();
            logger.info("Комментарий к посту - " + postComment.getPost().getText() + " успешно добавлен");
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add comment");
            throw new DAOException("Can not add comment", e);
        }
    }


    @Override
    public List<PostComment> read() {
        String sql = "SELECT * FROM post_comments";
        List<PostComment> postComments = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PostComment postComment = new PostComment();
                postComment.setId(resultSet.getInt("id"));
                postComment.setText(resultSet.getString("text"));
                postComment.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                postComment.setPost(DAOFactory.getInstance().getPostDAO().findPostById(resultSet.getString("post_id")));
                postComment.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));
                postComments.add(postComment);
                logger.info(postComment.toString());
            }
            if (!resultSet.next()) {
                logger.info("No comments");
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not read comments");
            throw new DAOException("Can not read comments", e);
        }
        return postComments;
    }


    @Override
    public PostComment findCommentByText(String text) {
        PostComment postComment = null;
        String sql = "SELECT * FROM post_comments WHERE text = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, text);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                postComment = new PostComment();
                postComment.setId(resultSet.getInt("id"));
                postComment.setText(resultSet.getString("text"));
                postComment.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                postComment.setPost(DAOFactory.getInstance().getPostDAO().findPostById(resultSet.getString("post_id")));
                postComment.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));

            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find comment");
            throw new DAOException("Can not find comment", e);
        }
        return postComment;
    }


    @Override
    public void updatePostComment(PostComment postComment) {
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            String sql = "UPDATE post_comments SET text = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, postComment.getText());
            statement.setInt(2, postComment.getId());
            statement.executeUpdate();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update comment");
            throw new DAOException("Can not update comment", e);
        }
    }


    @Override
    public void deletePostComment(PostComment postComment) {
        try {
            String sql = "DELETE FROM post_comments WHERE text = ?";
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, postComment.getText());
            statement.execute();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not delete comment");
            throw new DAOException("Can not delete comment", e);
        }
    }
}
