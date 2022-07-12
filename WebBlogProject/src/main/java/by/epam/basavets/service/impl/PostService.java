package by.epam.basavets.service.impl;

import by.epam.basavets.bean.*;
import by.epam.basavets.dao.PostCommentDAO;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.PostVoteDAO;
import by.epam.basavets.dao.impl.SettingsDAO;
import by.epam.basavets.dao.impl.UserDAO;
import by.epam.basavets.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class PostService implements by.epam.basavets.service.PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final PostCommentDAO postCommentDAO;
    private final PostVoteDAO postVoteDAO;
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostService(PostDAO postDAO, UserDAO userDAO, PostCommentDAO postCommentDAO, PostVoteDAO postVoteDAO, SettingsDAO settingsDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.postCommentDAO = postCommentDAO;
        this.postVoteDAO = postVoteDAO;
        this.settingsDAO = settingsDAO;
    }

    @Override
    public void addNewPost(String email, String title, String text) {
        try {
            if (settingsDAO.getSettings().equals(SettingStatus.Yes)) {
                Post post = new Post();
                User user = userDAO.findUserByEmail(email);
                post.setUser(user);
                post.setTitle(title);
                post.setText(text);
                post.setLikeCount(0);
                post.setDislikeCount(0);
                post.setViewCount(0);
                post.setTime(LocalDateTime.now());
                post.setModeratorStatus(ModeratorStatus.NEW);
                postDAO.addPost(post);
            } else {
                logger.error("Adding posts is denied access");
            }
        } catch (Exception e) {
            logger.error("Can not add post");
            throw new ServiceException("Can not add post", e);
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postDAO.readPosts();
    }

    @Override
    public Post getPostByTitle(String title) {
        return postDAO.findPostByTitle(title);
    }

    @Override
    public Post getPostById(String id) {
        return postDAO.findPostById(id);
    }

    @Override
    public void deletePostByTitle(String title) {
        try {
            Post post = postDAO.findPostByTitle(title);
            List<PostComment> postComments = postCommentDAO.read();
            List<PostVote> postVotes = postVoteDAO.readPostVotes();
            if (postComments.size() != 0) {
                postComments.stream().filter(postComment -> postComment.getPost().getId().equals(post.getId()))
                        .forEach(postCommentDAO::deletePostComment);
            }
            if (postVotes.size() != 0) {
                postVotes.stream().filter(postVote -> postVote.getPost().getId().equals(post.getId()))
                        .forEach(postVoteDAO::delete);
            }
            postDAO.deletePost(post);
            logger.info("Post deleted");
        } catch (Exception e) {
            logger.error("Can not delete post");
            throw new ServiceException("Can not delete post", e);
        }
    }

    @Override
    public void updatePostByTitle(String title, String newTitle, String text) {
        try {
            Post post = postDAO.findPostByTitle(title);
            post.setId(post.getId());
            post.setTitle(newTitle);
            post.setText(text);
            post.setModeratorStatus(ModeratorStatus.NEW);
            postDAO.updatePost(post);
            logger.info("Post updated");
        } catch (Exception e) {
            logger.error("Can not update post");
            throw new ServiceException("Can not update post", e);
        }
    }

    @Override
    public void addPostView(String postId) {
        try {
            Post post = postDAO.findPostById(postId);
            post.setViewCount(post.getViewCount() + 1);
            postDAO.updatePostByView(post);
        } catch (Exception e) {
            logger.error("Can not add post view");
            throw new ServiceException("Can not add post view", e);
        }
    }
}
