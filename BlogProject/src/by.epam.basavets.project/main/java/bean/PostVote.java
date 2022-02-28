package bean;

import java.time.LocalDateTime;

public class PostVote {
    private int id;
    private int Value;
    private LocalDateTime time;
    private User user;
    private Post post;


    public PostVote(int id, int value, LocalDateTime time, User user, Post post) {
        this.id = id;
        Value = value;
        this.time = time;
        this.user = user;
        this.post = post;
    }

    public PostVote(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
