package by.epam.basavets.service;

import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.PostCommentDAO;
import by.epam.basavets.dao.impl.PostDAO;
import by.epam.basavets.dao.impl.SettingsDAO;
import by.epam.basavets.dao.impl.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final PostCommentDAO postCommentDAO;
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();


    public PostService(PostDAO postDAO, UserDAO userDAO, PostCommentDAO postCommentDAO, SettingsDAO settingsDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.postCommentDAO = postCommentDAO;
        this.settingsDAO = settingsDAO;
    }


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
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    public List<Post> getAllPosts() throws SQLException {
        return postDAO.readPosts();
    }


    public Post getPostByTitle(String title) throws SQLException {
        return postDAO.findPostByTitle(title);
    }


    public Post getPostById(String id) throws SQLException {
        return postDAO.findPostById(id);
    }


    public void deletePostByTitle(String title) {
        try {
            Post post = postDAO.findPostByTitle(title);
            List<PostComment>postComments = postCommentDAO.read();
            if (postComments.size() != 0){
                for (PostComment postComment : postComments){
                    if (post.getId() == postComment.getPost().getId()){
                        postCommentDAO.deletePostComment(postComment);
                    }
                }
            }
            postDAO.deletePost(post);
            logger.info("Post deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());

        }
    }


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
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }


    public void addPostView(String postId) {
        try {
            Post post = postDAO.findPostById(postId);
            post.setViewCount(post.getViewCount() + 1);
            postDAO.updatePostByView(post);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
