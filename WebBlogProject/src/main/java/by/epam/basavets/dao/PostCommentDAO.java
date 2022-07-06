package by.epam.basavets.dao;

import by.epam.basavets.bean.PostComment;

import java.util.List;

public interface PostCommentDAO {

    void addPostComment(PostComment postComment);

    List<PostComment> read();

    PostComment findCommentByText(String text);

    void updatePostComment(PostComment postComment);

    void deletePostComment(PostComment postComment);
}
