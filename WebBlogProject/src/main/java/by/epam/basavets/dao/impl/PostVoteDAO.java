package by.epam.basavets.dao.impl;

import by.epam.basavets.bean.PostVote;
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

public class PostVoteDAO implements by.epam.basavets.dao.PostVoteDAO {

    private final Logger logger = LogManager.getRootLogger();


    @Override
    public void addPostVote(PostVote postVote) {
        String sql = "INSERT INTO post_votes (value, time, post_id, user_id) " +
                "VALUES (?, ?, ?, ?)";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postVote.getValue());
            statement.setString(2, postVote.getTime().toString());
            statement.setInt(3, postVote.getPost().getId());
            statement.setInt(4, postVote.getUser().getId());
            statement.execute();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not add vote");
            throw new DAOException("Can not add vote", e);
        }
    }


    @Override
    public void delete(PostVote postVote) {
        String sql = "DELETE FROM post_votes WHERE id = ?";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postVote.getId());
            statement.execute();
            ConnectionPool.getInstance().givenAwayConnection(connection, statement);
        } catch (Exception e) {
            logger.error("Can not delete post_vote");
            throw new DAOException("Can not delete post_vote", e);
        }
    }


    @Override
    public List<PostVote> readPostVotes() {
        List<PostVote> postVotes = new ArrayList<>();
        String sql = "SELECT * FROM post_votes";
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PostVote postVote = new PostVote();
                postVote.setId(resultSet.getInt("id"));
                postVote.setValue(resultSet.getInt("value"));
                postVote.setTime(LocalDateTime.parse(resultSet.getString("time"), DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")));
                postVote.setPost(DAOFactory.getInstance().getPostDAO().findPostById(String
                        .valueOf(resultSet.getInt("post_id"))));
                postVote.setUser(DAOFactory.getInstance().getUserDAO().findUserById(resultSet.getInt("user_id")));
                postVotes.add(postVote);
            }
            ConnectionPool.getInstance().givenAwayConnection(connection, statement, resultSet);
        } catch (Exception e) {
            logger.error("Can not read posts");
            throw new DAOException("Can not read posts", e);
        }
        return postVotes;
    }
}