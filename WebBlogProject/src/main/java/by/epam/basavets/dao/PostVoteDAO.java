package by.epam.basavets.dao;

import by.epam.basavets.DBConnection;
import by.epam.basavets.bean.PostVote;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostVoteDAO implements Serializable {

    private final List<PostVote> postVotes = new ArrayList<>();
    private static final long serialVersionUID = 15L;

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
    }

    public List<PostVote> getPostVotes() {
        return postVotes;
    }
}
