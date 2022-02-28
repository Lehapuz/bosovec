package bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PostComment implements Serializable {

    private int id;
    private String text;
    private LocalDateTime time;
    private Post post;
    private User user;

    private static final long serialVersionUID = 4L;


    public PostComment(int id, String text, LocalDateTime time, Post post, User user) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.post = post;
        this.user = user;
    }

    public PostComment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Комментарий к посту - " + post.getTitle() + " от пользователя - " + user.getName() + ": " + getText();
    }
}
