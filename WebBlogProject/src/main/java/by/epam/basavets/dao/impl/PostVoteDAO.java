package by.epam.basavets.dao.impl;

import by.epam.basavets.bean.PostVote;
import by.epam.basavets.dao.ConnectionPool;
import by.epam.basavets.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            throw new DAOException(e.getMessage());
        }
    }
}