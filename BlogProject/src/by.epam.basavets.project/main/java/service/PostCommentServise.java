package service;

import bean.Post;
import bean.PostComment;
import dao.PostCommentDAO;
import dao.PostDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class PostCommentServise {

    private final PostDAO postDAO;
    private final PostCommentDAO postCommentDAO;
    private final Logger logger = LogManager.getRootLogger();

    public PostCommentServise(PostDAO postDAO, PostCommentDAO postCommentDAO) {
        this.postDAO = postDAO;
        this.postCommentDAO = postCommentDAO;
    }


    public void addPostComment(String title, String textComment) {
        try {
            PostComment postComment = new PostComment();
            int i = 0;
            Post post;
            post = postDAO.findPostByTitle(title);
            postComment.setId(++i);
            postComment.setPost(post);
            postComment.setText(textComment);
            postComment.setUser(post.getUser());
            postComment.setTime(LocalDateTime.now());
            post.setPostComments(post.getPostComments());
            postCommentDAO.addPostComment(postComment);
            logger.info("Комментарий успешно добавлен");
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }

    public void getAllPostComments() {
        postCommentDAO.read();
    }

    public void deletePostComment(String textComment) {
        try {
            PostComment postComment;
            postComment = postCommentDAO.findCommentByText(textComment);
            postCommentDAO.deletePostComment(postComment);
        } catch (Exception e) {
            logger.error("Такого комментария не существует");
        }
    }

    public void updatePostCommentByText(String textComment, String newTextComment) {
        try {
            PostComment postComment;
            PostComment postComment1 = new PostComment();
            postComment = postCommentDAO.findCommentByText(textComment);
            postComment1.setId(postComment.getId());
            postComment1.setPost(postComment.getPost());
            postComment1.setUser(postComment.getUser());
            postCommentDAO.updatePostComment(postComment, postComment1);
            postComment1.setText(newTextComment);
        } catch (Exception e) {
            logger.error("Такого поста не существует");
        }
    }
}
