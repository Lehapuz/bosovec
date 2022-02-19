package Service;

import Bean.Post;
import Bean.PostComment;
import DAO.PostCommentDAO;
import DAO.PostDAO;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PostCommentServise {

    private final PostDAO postDAO;
    private final PostCommentDAO postCommentDAO;

    public PostCommentServise(PostDAO postDAO, PostCommentDAO postCommentDAO) {
        this.postDAO = postDAO;
        this.postCommentDAO = postCommentDAO;
    }

    Scanner scanner = new Scanner(System.in);


    public void addPostComment() {
        try {
            PostComment postComment = new PostComment();
            int i = 0;
            Post post;
            System.out.println("Выберите пост по названию");
            String input = scanner.nextLine();
            post = postDAO.findPostByTitle(input);
            System.out.println("Добавьте комментарий");
            input = scanner.nextLine();
            postComment.setId(++i);
            postComment.setPost(post);
            postComment.setText(input);
            postComment.setUser(post.getUser());
            postComment.setTime(LocalDateTime.now());
            post.setPostComments(post.getPostComments());
            postCommentDAO.addPostComment(postComment);
            System.out.println("Комментарий успешно добавлен");
        } catch (Exception e) {
            System.out.println("Такого поста не существует");
        }
    }


    public void getAllPostComments() {
        postCommentDAO.read();
    }

    public void deletePostComment() {
        try {
            System.out.println("Введите текст комментария который хотите удалить");
            PostComment postComment;
            String input = scanner.nextLine();
            postComment = postCommentDAO.findCommentByText(input);
            postCommentDAO.deletePostComment(postComment);
        } catch (Exception e) {
            System.out.println("Такого комментария не существует");
        }
    }

    public void updatePostCommentByText() {
        try {
            System.out.println("Введите текст комментария который хотите откорректировать");
            PostComment postComment;
            PostComment postComment1 = new PostComment();
            String input = scanner.nextLine();
            postComment = postCommentDAO.findCommentByText(input);
            postComment1.setId(postComment.getId());
            postComment1.setPost(postComment.getPost());
            postComment1.setUser(postComment.getUser());
            postCommentDAO.updatePostComment(postComment, postComment1);
            System.out.println("Введите комментарий");
            input = scanner.nextLine();
            postComment1.setText(input);
        } catch (Exception e) {
            System.out.println("Такого поста не существует");
        }
    }
}
