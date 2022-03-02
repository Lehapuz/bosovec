package dao;

import bean.PostComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostCommentDAO implements Serializable {

    List<PostComment> postComments = new ArrayList<>();
    private final Logger logger = LogManager.getRootLogger();
    private static final long serialVersionUID = 13L;

    public void addPostComment(PostComment postComment) {
        postComments.add(postComment);
    }

    public void read() {
        if (postComments.size() == 0) {
            logger.info("Комментарии отсутствуют");
        }
        for (PostComment postComment : postComments) {
            logger.info(postComment.toString());
        }
    }

    public PostComment findCommentByText(String text) {
        for (PostComment postComment : postComments) {
            if (postComment.getText().equals(text)) {
                return postComment;
            }
        }
        return null;
    }

    public void updatePostComment(PostComment postComment, PostComment newPostComment) {
        postComments.removeIf(postComment1 -> postComment1.equals(postComment));
        postComments.add(newPostComment);
    }

    public void deletePostComment(PostComment postComment) {
        postComments.removeIf(postComment1 -> postComment1.equals(postComment));
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }
}
