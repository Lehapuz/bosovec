package bean;

import bean.Enum.ModeratorStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Post implements Serializable {
    private int id;
    private String title;
    private String text;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
    private LocalDateTime time;
    private User user;
    private List<PostComment> postComments;
    private ModeratorStatus moderatorStatus;

    private static final long serialVersionUID = 3L;

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

    public Post(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
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
            return "Оглавление поста  - " + title + " Текст поста - " + text +
                    " дата добавления поста - " + time + " количество просмостров - " + viewCount +
                    " количество лайков - " + likeCount + " количество дизлайков - " + dislikeCount +
                    " автор поста - " + user;
    }

    public ModeratorStatus getModeratorStatus() {
        return moderatorStatus;
    }

    public void setModeratorStatus(ModeratorStatus moderatorStatus) {
        this.moderatorStatus = moderatorStatus;
    }
}
