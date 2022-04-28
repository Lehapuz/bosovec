package by.epam.basavets.dao;

import by.epam.basavets.utils.DBConnection;
import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostCommentDAO {

    private final Logger logger = LogManager.getRootLogger();


    public void addPostComment(PostComment postComment) throws SQLException {
        String sql = "INSERT INTO post_comments (text, time, post_id, user_id) " +
                "VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, postComment.getText());
        statement.setString(2, postComment.getTime().toString());
        statement.setInt(3, postComment.getPost().getId());
        statement.setInt(4, postComment.getUser().getId());
        statement.execute();
        logger.info("Комментарий к посту - " + postComment.getPost().getText() + " успешно добавлен");
        statement.close();
        connection.close();
    }


    public Post findPostById(int id) throws SQLException {
        String sql = "SELECT * FROM posts WHERE id = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
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
            post.setUser(findUserById(resultSet.getInt("user_id")));
            post.setModeratorStatus(ModeratorStatus.valueOf(resultSet.getString("moderator_status")));
            return post;
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }


    public User findUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRegTime(LocalDateTime.parse(resultSet.getString("registration_time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            return user;
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }


    public void read() throws SQLException {
        String sql = "SELECT * FROM post_comments";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            PostComment postComment = new PostComment();
            postComment.setId(resultSet.getInt("id"));
            postComment.setText(resultSet.getString("text"));
            postComment.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            postComment.setPost(findPostById(resultSet.getInt("post_id")));
            postComment.setUser(findUserById(resultSet.getInt("user_id")));
            logger.info(postComment.toString());
        }
        if (!resultSet.next()) {
            logger.info("Комментарии отсутствуют");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }


    public PostComment findCommentByText(String text) throws SQLException {
        String sql = "SELECT * FROM post_comments WHERE text = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, text);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            PostComment postComment = new PostComment();
            postComment.setId(resultSet.getInt("id"));
            postComment.setText(resultSet.getString("text"));
            postComment.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")));
            postComment.setPost(findPostById(resultSet.getInt("post_id")));
            postComment.setUser(findUserById(resultSet.getInt("user_id")));
            return postComment;
        }
        resultSet.close();
        statement.close();
        connection.close();
        return null;
    }


    public void updatePostComment(PostComment postComment) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "UPDATE post_comments SET text = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, postComment.getText());
        statement.setInt(2, postComment.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }


    public void deletePostComment(PostComment postComment) throws SQLException {
        String sql = "DELETE FROM post_comments WHERE text = ?";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, postComment.getText());
        statement.execute();
        statement.close();
        connection.close();
    }
}
