package service;

import bean.Post;
import bean.PostComment;
import dao.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;


public class PostCommentService {

    private final DataSource dataSource;
    private final Logger logger = LogManager.getRootLogger();
    private static int i = 0;

    public PostCommentService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void addPostComment(String title, String textComment) {
        try {
            PostComment postComment = new PostComment();
            Post post;
            post = dataSource.getPostDAO().findPostByTitle(title);
            postComment.setId(++i);
            postComment.setPost(post);
            postComment.setText(textComment);
            postComment.setUser(post.getUser());
            postComment.setTime(LocalDateTime.now());
            post.setPostComments(post.getPostComments());
            dataSource.getPostCommentDAO().addPostComment(postComment);
            logger.info("Комментарий успешно добавлен");
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }

    public void getAllPostComments() {
        dataSource.getPostCommentDAO().read();
    }

    public void deletePostComment(String textComment) {
        try {
            PostComment postComment;
            postComment = dataSource.getPostCommentDAO().findCommentByText(textComment);
            dataSource.getPostCommentDAO().deletePostComment(postComment);
        } catch (Exception e) {
            logger.error("Такого комментария не существует");
        }
    }

    public void updatePostCommentByText(String textComment, String newTextComment) {
        try {
            PostComment postComment;
            PostComment postComment1 = new PostComment();
            postComment = dataSource.getPostCommentDAO().findCommentByText(textComment);
            postComment1.setId(postComment.getId());
            postComment1.setPost(postComment.getPost());
            postComment1.setUser(postComment.getUser());
            dataSource.getPostCommentDAO().updatePostComment(postComment, postComment1);
            postComment1.setText(newTextComment);
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }
}
