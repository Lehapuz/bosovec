package by.epam.basavets.service;

import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Enum.SettingStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.User;
import by.epam.basavets.dao.PostDAO;
import by.epam.basavets.dao.SettingsDAO;
import by.epam.basavets.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();

    public PostService(PostDAO postDAO, UserDAO userDAO, SettingsDAO settingsDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
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
                logger.error("На добавление новых постов доступ запрещен");
            }
        } catch (Exception e) {
            logger.error("Настройки сайта не заданы обратитесь к модератору");
        }
    }


    public List<Post> getAllPosts() throws SQLException {
//        List<Post> posts;
//        posts = postDAO.readPosts();
//        for (Post post : posts) {
//            if (post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED)) {
//                posts.add(post);
//                logger.info(post.toString());
//            }
//            if (post.getModeratorStatus().equals(ModeratorStatus.DECLINED)) {
//                logger.info("Пост отклонен модератором");
//            }
//            if (post.getModeratorStatus().equals(ModeratorStatus.NEW)) {
//                logger.info("Пост на модерации");
//            }
//        }
        return postDAO.readPosts();
    }


    public List<Post> getCorrectPosts() throws SQLException {
        List<Post> posts;
        List<Post> correctPost = new ArrayList<>();
        posts = getAllPosts();
        for (Post post : posts) {
            if (post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED)) {
                correctPost.add(post);
                logger.info(post.toString());
            }
        }
        return correctPost;
    }

    public List<String> getNonCorrectPosts() throws SQLException {
        List<Post> posts;
        List<String> nonCorrectPost = new ArrayList<>();
        posts = getAllPosts();
        for (Post post : posts) {
            if (post.getModeratorStatus().equals(ModeratorStatus.DECLINED)) {
                nonCorrectPost.add("Пост отклонен модератором");
                logger.info("Пост отклонен модератором");
            }
            if (post.getModeratorStatus().equals(ModeratorStatus.NEW)) {
                nonCorrectPost.add("Пост на модерации");
                logger.info("Пост на модерации");
            }
        }
        return nonCorrectPost;
    }


    public Post getPostByTitle(String title) throws SQLException {
        return postDAO.findPostByTitle(title);
    }


    public void deletePostByTitle(String title) {
        try {
            Post post = postDAO.findPostByTitle(title);
            postDAO.deletePost(post);
            logger.info("Пост удален");
        } catch (Exception e) {
            logger.error("Пост не найден");
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
            logger.info("Пост обновлен");
        } catch (Exception e) {
            logger.error("Пост не найден");
        }
    }
}
