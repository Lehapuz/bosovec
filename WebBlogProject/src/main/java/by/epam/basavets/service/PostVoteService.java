package by.epam.basavets.service;

import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostVote;
import by.epam.basavets.dao.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class PostVoteService {

    private final DataSource dataSource;
    private final Logger logger = LogManager.getRootLogger();
    private static int i = 0;

    public PostVoteService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setPostVote(String postTitle, String value) {
        try {
            PostVote postVote = new PostVote();
            Post post;
            post = dataSource.getPostDAO().findPostByTitle(postTitle);
            postVote.setValue(Integer.parseInt(value));
            postVote.setId(++i);
            postVote.setTime(LocalDateTime.now());
            postVote.setPost(post);
            postVote.setUser(post.getUser());
            dataSource.getPostVoteDAO().addPostVote(postVote);
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
