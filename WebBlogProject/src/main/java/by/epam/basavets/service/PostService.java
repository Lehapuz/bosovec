package by.epam.basavets.service;

import by.epam.basavets.bean.Post;

import java.util.List;

public interface PostService {

    void addNewPost(String email, String title, String text);

    List<Post> getAllPosts();

    Post getPostByTitle(String title);

    Post getPostById(String id);

    void deletePostByTitle(String title);

    void updatePostByTitle(String title, String newTitle, String text);

    void addPostView(String postId);

}
