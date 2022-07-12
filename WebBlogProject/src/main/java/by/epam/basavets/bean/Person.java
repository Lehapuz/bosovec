package by.epam.basavets.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class Person implements Comparable<Person>, Serializable {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registrationTime;
    private List<Post> posts;
    private List<PostComment> postComments;
    private Role role;
    private Integer active;
    private static final long serialVersionUID = 1122L;

    public Person(int id, String name, String email, String password, LocalDateTime regTime, Role role, int active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationTime = regTime;
        this.role = role;
        this.active = active;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hash(id, name, email, password, registrationTime, role, active);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(email,
                person.email) && Objects.equals(password, person.password) && Objects.equals(registrationTime,
                person.registrationTime) && Objects.equals(role, person.role) && Objects.equals(active, person.active);
    }

    @Override
    public String toString() {
        return "Имя пользователя  - " + name + " адресс электронной почты - " + email + " " +
                "дата регистрации - " + registrationTime;
    }
}
