package by.epam.basavets.dao.impl;

import by.epam.basavets.bean.ModeratorStatus;
import by.epam.basavets.bean.Post;
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

public class PostDAO implements by.epam.basavets.dao.PostDAO {

    private final Logger logger = LogManager.getRootLogger();


    @Override
    public void addPost(Post post) {
        String sql = "INSERT INTO posts (title, text, like_count, dislike_count, " +
                "view_count, time, user_id, moderator_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setInt(3, post.getLikeCount());
            statement.setInt(4, post.getDislikeCount());
            statement.setInt(5, post.getViewCount());
            statement.setString(6, LocalDateTime.now().toString());
            statement.setInt(7, post.getUser().getId());
            statement.setString(8, post.getModeratorStatus().toString());
            statement.execute();
            logger.info("Пост - " + post.getTitle() + " успешно добавлен");
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add post");
            throw new DAOException("Can not add post", e);
        }
    }


    @Override
    public List<Post> readPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setLikeCount(resultSet.getInt("like_count"));
                post.setDislikeCount(resultSet.getInt("dislike_count"));
                post.setViewCount(resultSet.getInt("view_count"));
                post.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                post.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));
                post.setModeratorStatus(ModeratorStatus.valueOf(resultSet.getString("moderator_status")));
                posts.add(post);
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not read posts");
            throw new DAOException("Can not read posts", e);
        }
        return posts;
    }


    @Override
    public void updatePostModeratorStatus(Post post) {
        String sql = "UPDATE posts SET moderator_status = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getModeratorStatus().toString());
            statement.setInt(2, post.getId());
            statement.executeUpdate();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update moderator status");
            throw new DAOException("Can not update moderator status", e);
        }
    }


    @Override
    public void updatePost(Post post) {
        String sql = "UPDATE posts SET title = ?, text = ?, moderator_status = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setString(3, post.getModeratorStatus().toString());
            statement.setInt(4, post.getId());
            statement.executeUpdate();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update post");
            throw new DAOException("Can not update post", e);
        }
    }


    @Override
    public void deletePost(Post post) {
        String sql = "DELETE FROM posts WHERE title = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getTitle());
            statement.execute();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not delete post");
            throw new DAOException("Can not delete post", e);
        }
    }


    @Override
    public Post findPostByTitle(String title) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE title = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setLikeCount(resultSet.getInt("like_count"));
                post.setDislikeCount(resultSet.getInt("dislike_count"));
                post.setViewCount(resultSet.getInt("view_count"));
                post.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                post.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));
                post.setModeratorStatus(ModeratorStatus.valueOf(resultSet.getString("moderator_status")));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find post by title");
            throw new DAOException("Can not find post by title", e);
        }
        return post;
    }


    @Override
    public Post findPostById(String id) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setLikeCount(resultSet.getInt("like_count"));
                post.setDislikeCount(resultSet.getInt("dislike_count"));
                post.setViewCount(resultSet.getInt("view_count"));
                post.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                post.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));
                post.setModeratorStatus(ModeratorStatus.valueOf(resultSet.getString("moderator_status")));
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not find post by id");
            throw new DAOException("Can not find post by id", e);
        }
        return post;
    }


    @Override
    public void updatePostByPostVote(Post post) {
        String sql = "UPDATE posts SET like_count = ?, dislike_count = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getLikeCount());
            statement.setInt(2, post.getDislikeCount());
            statement.setInt(3, post.getId());
            statement.executeUpdate();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update post by vote");
            throw new DAOException("Can not update post by vote", e);
        }
    }


    @Override
    public void updatePostByView(Post post) {
        String sql = "UPDATE posts SET view_count = ? WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, post.getViewCount());
            statement.setInt(2, post.getId());
            statement.executeUpdate();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not update post by view");
            throw new DAOException("Can not update post by view", e);
        }
    }
}
