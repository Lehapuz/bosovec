package by.epam.basavets.dao;

import by.epam.basavets.bean.Post;

import java.util.List;

public interface PostDAO {

    void addPost(Post post);

    List<Post> readPosts();

    void updatePostModeratorStatus(Post post);

    void updatePost(Post post);

    void deletePost(Post post);

    Post findPostByTitle(String title);

    Post findPostById(String id);

    void updatePostByPostVote(Post post);

    void updatePostByView(Post post);
}
