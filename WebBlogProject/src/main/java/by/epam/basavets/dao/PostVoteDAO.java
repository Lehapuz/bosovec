package by.epam.basavets.dao;

import by.epam.basavets.bean.PostVote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostVoteDAO implements Serializable {

    private final List<PostVote> postVotes = new ArrayList<>();
    private static final long serialVersionUID = 15L;

    public void addPostVote(PostVote postVote) {
        postVotes.add(postVote);
    }

    public List<PostVote> getPostVotes() {
        return postVotes;
    }
}
