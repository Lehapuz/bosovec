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
import java.util.List;

public class PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final SettingsDAO settingsDAO;
    private final Logger logger = LogManager.getRootLogger();
    private static int i = 0;

    public PostService(PostDAO postDAO, UserDAO userDAO, SettingsDAO settingsDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.settingsDAO = settingsDAO;
    }

    public void addNewPost(String email, String title, String text) {
        try {
            if (settingsDAO.getSettings().equals(SettingStatus.Yes)) {
                Post post = new Post();
                //List<PostComment> postComments = new ArrayList<>();
                User user = userDAO.findUserByEmail(email);
                post.setUser(user);
                post.setTitle(title);
                post.setText(text);
                post.setId(++i);
                post.setLikeCount(0);
                post.setDislikeCount(0);
                post.setViewCount(0);
                post.setTime(LocalDateTime.now());
                //post.setPostComments(postComments);
                post.setModeratorStatus(ModeratorStatus.NEW);
                postDAO.addPost(post);
                //logger.info("Пост добавлен");
            } else {
                logger.error("На добавление новых постов доступ запрещен");
            }
        } catch (Exception e) {
            logger.error("Настройки сайта не заданы обратитесь к модератору");
        }
    }


    public void getAllPosts() throws SQLException {
        List<Post> posts;
        posts = postDAO.readPosts();
        for (Post post : posts) {
            if (post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED)) {
                logger.info(post.toString());
            }
            if (post.getModeratorStatus().equals(ModeratorStatus.DECLINED)) {
                logger.info("Пост отклонен модератором");
            }
            if (post.getModeratorStatus().equals(ModeratorStatus.NEW)) {
                logger.info("Пост на модерации");
            }
        }
    }


//    public void deletePostByTitle(String title) {
//        try {
//            Post post;
//            post = dataSource.getPostDAO().findPostByTitle(title);
//            dataSource.getPostDAO().deletePost(post);
//            logger.info("Пост удален");
//        } catch (Exception e) {
//            logger.error("Пост не найден");
//        }
//    }
//
//
//    public void updatePostByTitle(String title, String newTitle, String text) {
//        try {
//            Post post;
//            Post post1 = new Post();
//            post = dataSource.getPostDAO().findPostByTitle(title);
//            post1.setId(post.getId());
//            post1.setUser(post.getUser());
//            post1.setPostComments(post.getPostComments());
//            post1.setLikeCount(post.getLikeCount());
//            post1.setDislikeCount(post.getDislikeCount());
//            post1.setViewCount(post.getViewCount());
//            post1.setTime(LocalDateTime.now());
//            post1.setModeratorStatus(ModeratorStatus.NEW);
//            dataSource.getPostDAO().updatePost(post, post1);
//            post1.setTitle(newTitle);
//            post1.setText(text);
//            logger.info("Пост обновлен");
//        } catch (Exception e) {
//            logger.error("Пост не найден");
//        }
//    }
}
