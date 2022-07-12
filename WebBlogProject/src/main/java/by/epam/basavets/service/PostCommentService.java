package by.epam.basavets.service;

import by.epam.basavets.bean.PostComment;

import java.util.List;

public interface PostCommentService {

    void addPostComment(String title, String email, String textComment);

    List<PostComment> getAllPostComments();

    void deletePostComment(String textComment, String email);

    PostComment getPostCommentByText(String textComment);

    void updatePostCommentByText(String textComment, String email, String newTextComment);
}
