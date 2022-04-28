package by.epam.basavets.dao;

import by.epam.basavets.utils.DBConnection;
import by.epam.basavets.bean.PostVote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostVoteDAO {


    public void addPostVote(PostVote postVote) throws SQLException {
        String sql = "INSERT INTO post_votes (value, time, post_id, user_id) " +
                "VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, postVote.getValue());
        statement.setString(2, postVote.getTime().toString());
        statement.setInt(3, postVote.getPost().getId());
        statement.setInt(4, postVote.getUser().getId());
        statement.execute();
        statement.close();
        connection.close();
    }
}
