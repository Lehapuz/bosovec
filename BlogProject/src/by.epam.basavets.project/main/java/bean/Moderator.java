package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Moderator extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    public Moderator(int id, String name, String email, String password, LocalDateTime registrationTime) {
        super(id, name, email, password, registrationTime);
    }

    public Moderator() {
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public LocalDateTime getRegTime() {
        return super.getRegTime();
    }

    @Override
    public void setRegTime(LocalDateTime regTime) {
        super.setRegTime(regTime);
    }

    @Override
    public List<Post> getPosts() {
        return super.getPosts();
    }

    @Override
    public void setPosts(List<Post> posts) {
        super.setPosts(posts);
    }

    @Override
    public List<PostComment> getPostComments() {
        return super.getPostComments();
    }

    @Override
    public void setPostComments(List<PostComment> postComments) {
        super.setPostComments(postComments);
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

    @Override
    public int compareTo(Person o) {
        return Integer.compare(getId(), o.getId());
    }
}
