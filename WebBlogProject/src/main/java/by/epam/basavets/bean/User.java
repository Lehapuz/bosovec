package by.epam.basavets.bean;

import java.time.LocalDateTime;
import java.util.List;

public class User extends Person {

    public User(Integer id, String name, String email, String password, LocalDateTime registrationTime, Role role, Integer active) {
        super(id, name, email, password, registrationTime, role, active);
    }

    public User() {
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
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
    public Role getRole() {
        return super.getRole();
    }

    @Override
    public void setRole(Role role) {
        super.setRole(role);
    }

    @Override
    public Integer getActive() {
        return super.getActive();
    }

    @Override
    public void setActive(Integer active) {
        super.setActive(active);
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
