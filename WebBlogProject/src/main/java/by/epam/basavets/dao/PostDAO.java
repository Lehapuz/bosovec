package by.epam.basavets.dao;

import by.epam.basavets.DBConnection;
import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Moderator;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements Serializable {
    private final Logger logger = LogManager.getRootLogger();
    private static final long serialVersionUID = 14L;


    public void addPost(Post post) throws SQLException {
        String sql = "INSERT INTO posts (title, text, like_count, dislike_count, " +
                "view_count, time, user_id, moderator_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
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
    }



    public List<Post> readPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet == null) {
            logger.info("Посты отсутствуют");
        } else {
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
                posts.add(post);
            }
        }
      return posts;
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
        return null;
    }



    public void updatePost(Post post, Post newPost) throws SQLException {
        readPosts().removeIf(post1 -> post1.equals(post));
        readPosts().add(newPost);
    }

    public void deletePost(Post post) throws SQLException {
        readPosts().removeIf(user1 -> user1.equals(post));
    }


    public Post findPostByTitle(String title) throws SQLException {
        for (Post post : readPosts()) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }
}
