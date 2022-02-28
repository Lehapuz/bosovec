package dao;

import bean.PostVote;

import java.util.ArrayList;
import java.util.List;

public class PostVoteDAO {

    List<PostVote> postVotes = new ArrayList<>();

    public void addPostVote(PostVote postVote) {
        postVotes.add(postVote);
    }

    public List<PostVote> getPostVotes() {
        return postVotes;
    }
}
