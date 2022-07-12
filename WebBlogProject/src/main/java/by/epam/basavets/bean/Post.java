package by.epam.basavets.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Post implements Serializable {
    private Integer id;
    private String title;
    private String text;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;
    private LocalDateTime time;
    private User user;
    private List<PostComment> postComments;
    private ModeratorStatus moderatorStatus;
    private static final long serialVersionUID = 1144L;

    public Post(int id, String title, String text, int likeCount, int dislikeCount, int viewCount,
                LocalDateTime time, User user, ModeratorStatus moderatorStatus) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
        this.time = time;
        this.user = user;
        this.moderatorStatus = moderatorStatus;
    }

    public Post() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public ModeratorStatus getModeratorStatus() {
        return moderatorStatus;
    }

    public void setModeratorStatus(ModeratorStatus moderatorStatus) {
        this.moderatorStatus = moderatorStatus;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : Objects.hash(id, title, text, likeCount, dislikeCount, viewCount, time, user,
                moderatorStatus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Post post = (Post) obj;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(text,
                post.text) && Objects.equals(likeCount, post.likeCount) && Objects.equals(dislikeCount,
                post.dislikeCount) && Objects.equals(viewCount, post.viewCount) && Objects.equals(time, post.time)
                && Objects.equals(user, post.user) && Objects.equals(moderatorStatus, post.moderatorStatus);
    }

    @Override
    public String toString() {
        return title + " дата добавления поста - " + time + " количество просмостров - "
                + viewCount + " количество лайков - " + likeCount + " количество дизлайков - " + dislikeCount +
                " автор поста - " + user.getName();
    }
}
