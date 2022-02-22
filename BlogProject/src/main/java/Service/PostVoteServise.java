package Service;

import Bean.Post;
import Bean.PostVote;
import DAO.PostDAO;
import DAO.PostVoteDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PostVoteServise {

    private final PostDAO postDAO;
    private final PostVoteDAO postVoteDAO;
    private final Logger logger = LogManager.getRootLogger();

    public PostVoteServise(PostDAO postDAO, PostVoteDAO postVoteDAO) {
        this.postDAO = postDAO;
        this.postVoteDAO = postVoteDAO;
    }

    Scanner scanner = new Scanner(System.in);


    public void setPostVote() {
        try {
            PostVote postVote = new PostVote();
            int i = 0;
            Post post;
            logger.info("Проголосуйте за пост");
            String input = scanner.nextLine();
            post = postDAO.findPostByTitle(input);
            logger.info("Оцените пост");
            input = scanner.nextLine();
            postVote.setValue(Integer.parseInt(input));
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
