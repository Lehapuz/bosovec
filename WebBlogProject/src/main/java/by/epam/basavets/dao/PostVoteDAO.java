package by.epam.basavets.dao;

import by.epam.basavets.bean.PostVote;

import java.util.List;

public interface PostVoteDAO {

    void addPostVote(PostVote postVote);

    void delete(PostVote postVote);

    List<PostVote> readPostVotes();
}
