package Service;

import Bean.Enum.ModeratorStatus;
import Bean.Enum.SettingStatus;
import Bean.Post;
import Bean.PostComment;
import Bean.User;
import DAO.PostDAO;
import DAO.SettingsDAO;
import DAO.UserDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostServise {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final SettingsDAO settingsDAO;

    public PostServise(PostDAO postDAO, UserDAO userDAO, SettingsDAO settingsDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.settingsDAO = settingsDAO;
    }

    Scanner scanner = new Scanner(System.in);


    public void addNewPost() {
        try {
            if (settingsDAO.getSettings().equals(SettingStatus.Yes)) {
                Post post = new Post();
                int i = 0;
                List<PostComment> postComments = new ArrayList<>();
                System.out.println("Введите адрес электронной почты");
                String input = scanner.nextLine();
                User user = userDAO.findUserByEmail(input);
                post.setUser(user);
                System.out.println("Введите заголовок");
                input = scanner.nextLine();
                post.setTitle(input);
                System.out.println("Введите текст");
                input = scanner.nextLine();
                post.setText(input);
                post.setId(++i);
                post.setLikeCount(0);
                post.setDislikeCount(0);
                post.setViewCount(0);
                post.setTime(LocalDateTime.now());
                post.setPostComments(postComments);
                post.setModeratorStatus(ModeratorStatus.NEW);
                postDAO.addPost(post);
                System.out.println("Пост добавлен");
            } else {
                System.out.println("На добавление новых постов доступ запрещен");
            }
        } catch (Exception e) {
            System.out.println("Настройки сайта не заданы обратитесь к модератору");
        }
    }


    public void getAllPosts() {
        List<Post> posts;
        posts = postDAO.read();
        for (Post post : posts) {
            if (post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED)) {
                System.out.println(post.toString());
            }
            if (post.getModeratorStatus().equals(ModeratorStatus.DECLINED)) {
                System.out.println("Пост отклонен модератором");
            }
            if (post.getModeratorStatus().equals(ModeratorStatus.NEW)) {
                System.out.println("Пост на модерации");
            }
        }
    }


    public void deletePostByTitle() {
        try {
            Post post;
            System.out.println("Введите название который хотите удалить");
            String input = scanner.nextLine();
            post = postDAO.findPostByTitle(input);
            postDAO.deletePost(post);
            System.out.println("Пост удален");
        } catch (Exception e) {
            System.out.println("Пост не найден");
        }
    }


    public void updatePostByTitle() {
        try {
            System.out.println("Введите название  который хотите откорректировать");
            Post post;
            Post post1 = new Post();
            String input = scanner.nextLine();
            post = postDAO.findPostByTitle(input);
            post1.setId(post.getId());
            post1.setUser(post.getUser());
            post1.setPostComments(post.getPostComments());
            post1.setLikeCount(post.getLikeCount());
            post1.setDislikeCount(post.getDislikeCount());
            post1.setViewCount(post.getViewCount());
            post1.setTime(LocalDateTime.now());
            post1.setModeratorStatus(ModeratorStatus.NEW);
            postDAO.updatePost(post, post1);
            System.out.println("Введите новое название");
            input = scanner.nextLine();
            post1.setTitle(input);
            System.out.println("Введите новый текст");
            input = scanner.nextLine();
            post1.setText(input);
            System.out.println("Пост обновлен");
        } catch (Exception e) {
            System.out.println("Пост не найден");
        }
    }
}
