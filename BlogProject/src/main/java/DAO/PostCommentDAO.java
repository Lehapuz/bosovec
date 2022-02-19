package DAO;

import Bean.PostComment;

import java.util.ArrayList;
import java.util.List;

public class PostCommentDAO {

    List<PostComment> postComments = new ArrayList<>();

    public void addPostComment(PostComment postComment) {
        postComments.add(postComment);
    }

    public void read() {
        if (postComments.size() == 0) {
            System.out.println("Комментарии отсутствуют");
        }
        for (PostComment postComment : postComments) {
            System.out.println(postComment.toString());
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
