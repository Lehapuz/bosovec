package service;

import bean.Post;
import bean.PostVote;
import dao.PostDAO;
import dao.PostVoteDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class PostVoteServise {

    private final PostDAO postDAO;
    private final PostVoteDAO postVoteDAO;
    private final Logger logger = LogManager.getRootLogger();

    public PostVoteServise(PostDAO postDAO, PostVoteDAO postVoteDAO) {
        this.postDAO = postDAO;
        this.postVoteDAO = postVoteDAO;
    }

    public void setPostVote(String postTitle, String value) {
        try {
            PostVote postVote = new PostVote();
            int i = 0;
            Post post;
            post = postDAO.findPostByTitle(postTitle);
            postVote.setValue(Integer.parseInt(value));
            postVote.setId(++i);
            postVote.setTime(LocalDateTime.now());
            postVote.setPost(post);
            postVote.setUser(post.getUser());
            postVoteDAO.addPostVote(postVote);
            if (postVote.getValue() == 1) {
                post.setLikeCount(post.getLikeCount() + 1);
            }
            if (postVote.getValue() == -1) {
                post.setDislikeCount(post.getDislikeCount() + 1);
            }
            post.setViewCount(post.getViewCount() + 1);
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }
}