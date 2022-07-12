package by.epam.basavets.service.impl;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostVote;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.PostVoteDAO;
import by.epam.basavets.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class PostVoteService implements by.epam.basavets.service.PostVoteService {

    private final PostVoteDAO postVoteDAO;
    private final PostDAO postDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostVoteService(PostVoteDAO postVoteDAO, PostDAO postDAO) {
        this.postVoteDAO = postVoteDAO;
        this.postDAO = postDAO;
    }


    public void setPostVote(String postId, String value) {
        try {
            PostVote postVote = new PostVote();
            Post post = postDAO.findPostById(postId);
            postVote.setValue(Integer.parseInt(value));
            postVote.setTime(LocalDateTime.now());
            postVote.setPost(post);
            postVote.setUser(post.getUser());
            postVoteDAO.addPostVote(postVote);
            if (postVote.getValue() == 1) {
                post.setLikeCount(post.getLikeCount() + 1);
                post.setDislikeCount(post.getDislikeCount());
                postDAO.updatePostByPostVote(post);
            }
            if (postVote.getValue() == -1) {
                post.setLikeCount(post.getLikeCount());
                post.setDislikeCount(post.getDislikeCount() + 1);
                postDAO.updatePostByPostVote(post);
            }
        } catch (Exception e) {
            logger.error("Can not set vote");
            throw new ServiceException("Can not set vote", e);
        }
    }
}
