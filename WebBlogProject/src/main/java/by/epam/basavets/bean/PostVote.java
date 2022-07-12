package by.epam.basavets.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class PostVote implements Serializable {
    private Integer id;
    private Integer value;
    private LocalDateTime time;
    private User user;
    private Post post;
    private static final long serialVersionUID = 1166L;


    public PostVote(int id, int value, LocalDateTime time, User user, Post post) {
        this.id = id;
        this.value = value;
        this.time = time;
        this.user = user;
        this.post = post;
    }

    public PostVote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hash(id, value, time, post, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PostVote postVote = (PostVote) obj;
        return Objects.equals(id, postVote.id) && Objects.equals(value, postVote.value) && Objects.equals(time,
                postVote.time) && Objects.equals(user, postVote.user) && Objects.equals(post, postVote.post);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
