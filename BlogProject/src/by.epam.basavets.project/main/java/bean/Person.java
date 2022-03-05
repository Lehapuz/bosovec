package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Person implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registrationTime;
    private List<Post> posts;
    private List<PostComment> postComments;
    private static final long serialVersionUID = 1122L;

    public Person(int id, String name, String email, String password, LocalDateTime regTime){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationTime = regTime;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegTime() {
        return registrationTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.registrationTime = regTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
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
        return "Имя пользователя  - " + name + " адресс электронной почты - " + email + " " +
                "дата регистрации - " + registrationTime;
    }
}
