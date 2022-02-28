package dao;

import bean.Post;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    List<Post> posts = new ArrayList<>();
    private final Logger logger = LogManager.getRootLogger();

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> read() {
        if (posts.size() == 0) {
            logger.info("Посты отсутствуют");
        }
        return posts;
    }

    public void updatePost(Post post, Post newPost) {
        posts.removeIf(post1 -> post1.equals(post));
        posts.add(newPost);
    }

    public void deletePost(Post post) {
        posts.removeIf(user1 -> user1.equals(post));
    }

    public Post findPostByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
