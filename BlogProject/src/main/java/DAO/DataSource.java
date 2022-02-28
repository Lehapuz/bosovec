package DAO;

import Bean.*;

import java.util.List;
import java.util.Set;

public class DATASource {
    private final List<Moderator> moderators;
    private final List<PostComment> postComments;
    private final List<Post> posts;
    private final List<PostVote> postVotes;
    private final Set<Settings> settingsSet;
    private final List<User> users;

    ModeratorDAO moderatorDAO;
    PostVoteDAO postVoteDAO;
    PostCommentDAO postCommentDAO;
    PostDAO postDAO;
    SettingsDAO settingsDAO;
    UserDAO userDAO;




    public DATASource(List<Moderator> moderators, List<PostComment> postComments,
                      List<Post> posts, List<PostVote> postVotes,
                      Set<Settings> settingsSet, List<User> users) {
        this.moderators = moderators;
        this.postComments = postComments;
        this.posts = posts;
        this.postVotes = postVotes;
        this.settingsSet = settingsSet;
        this.users = users;
    }


    public List<Moderator> getModerators() {
        return moderatorDAO.getModerators();
    }

    public List<PostComment> getPostComments() {
        return postCommentDAO.getPostComments();
    }

    public List<Post> getPosts() {
        return postDAO.getPosts();
    }

    public List<PostVote> getPostVotes() {
        return postVoteDAO.getPostVotes();
    }

    public Set<Settings> getSettingsSet() {
        return settingsDAO.getSettingsSet();
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
