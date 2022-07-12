package by.epam.basavets.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PostComment implements Serializable {

    private Integer id;
    private String text;
    private LocalDateTime time;
    private Post post;
    private User user;
    private static final long serialVersionUID = 1155L;


    public PostComment(int id, String text, LocalDateTime time, Post post, User user) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.post = post;
        this.user = user;
    }

    public PostComment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hash(id, text, time, post, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PostComment postComment = (PostComment) obj;
        return Objects.equals(id, postComment.id) && Objects.equals(text, postComment.text) && Objects.equals(time,
                postComment.time) && Objects.equals(user, postComment.user) && Objects.equals(post, postComment.post);
    }

    @Override
    public String toString() {
        return "Комментарий к посту - " + post.getTitle() + " от пользователя - " + user.getName() + ": " + getText();
    }
}
