package by.epam.basavets.service;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostVote;
import by.epam.basavets.dao.PostDAO;
import by.epam.basavets.dao.PostVoteDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class PostVoteService {

    private final PostVoteDAO postVoteDAO;
    private final PostDAO postDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostVoteService(PostVoteDAO postVoteDAO, PostDAO postDAO) {
        this.postVoteDAO = postVoteDAO;
        this.postDAO = postDAO;
    }


    public void setPostVote(String postTitle, String value) {
        try {
            PostVote postVote = new PostVote();
            Post post = postDAO.findPostByTitle(postTitle);
            postVote.setValue(Integer.parseInt(value));
            postVote.setTime(LocalDateTime.now());
            postVote.setPost(post);
            postVote.setUser(post.getUser());
            postVoteDAO.addPostVote(postVote);
            if (postVote.getValue() == 1) {
                post.setLikeCount(post.getLikeCount() + 1);
                post.setDislikeCount(post.getDislikeCount());
                post.setViewCount(post.getViewCount() + 1);
                postDAO.updatePostByPostVote(post);
            }
            if (postVote.getValue() == -1) {
                post.setLikeCount(post.getLikeCount());
                post.setDislikeCount(post.getDislikeCount() + 1);
                post.setViewCount(post.getViewCount() + 1);
                postDAO.updatePostByPostVote(post);
            }
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }
}
