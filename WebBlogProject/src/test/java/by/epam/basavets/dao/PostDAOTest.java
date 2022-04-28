package by.epam.basavets.dao;

import by.epam.basavets.bean.Enum.ModeratorStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.User;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAOTest extends TestCase {

    User user = new User(1, "Саша", "sasha@mail.ru", "987654321", LocalDateTime.now());
    User user1 = new User(1, "Дима", "dima@mail.ru", "987654321", LocalDateTime.now());

    ModeratorStatus moderatorStatus;

    Post post1 = new Post(1, "Пост №1", "Текст поста", 0, 0, 0, LocalDateTime.now(), user, moderatorStatus);
    Post post2 = new Post(2, "Пост №2", "Текст поста", 0, 0, 0, LocalDateTime.now(), user, moderatorStatus);
    Post post3 = new Post(3, "Пост №3", "Текст поста", 0, 0, 0, LocalDateTime.now(), user1, moderatorStatus);
    Post post4 = new Post(4, "Пост №4", "Текст поста", 0, 0, 0, LocalDateTime.now(), user1, moderatorStatus);

    PostDAO postDAO = new PostDAO();
    List<Post> expected;
    List<Post> actual;

    UserDAO userDAO = new UserDAO();

    List<User> expectedUser;
    List<User> actualUser;

    protected void setUp() throws SQLException {
        expected = new ArrayList<>();
        expected.add(post1);
        expected.add(post2);
        expected.add(post3);
        actual = new ArrayList<>();
        postDAO.addPost(post1);
        postDAO.addPost(post2);
        postDAO.addPost(post3);

        expectedUser = new ArrayList<>();
        expectedUser.add(user);
        expectedUser.add(user1);
        actualUser = new ArrayList<>();
        userDAO.addUser(user);
        userDAO.addUser(user1);
    }

    public void testReadPost() throws SQLException {
        actual = postDAO.readPosts();
        assertEquals(expected, actual);
    }

    public void testUpdatePostByEmail() throws SQLException {
        expected.remove(post1);
        expected.add(post4);
        postDAO.updatePost(post1);
        actual = postDAO.readPosts();
        assertEquals(expected, actual);
    }

    public void testDeletePostByEmail() throws SQLException {
        expected.remove(post2);
        postDAO.deletePost(post2);
        actual = postDAO.readPosts();
        assertEquals(expected, actual);
    }

    public void testFindPostByTitle() throws SQLException {
        Post expected = post3;
        Post actual = postDAO.findPostByTitle("Пост №3");
        assertEquals(expected, actual);
    }

    public void testFindUserByEmail() throws SQLException {
        User expectedUser = user;
        User actualUser = userDAO.findUserByEmail("sasha@mail.ru");
        System.out.println(expectedUser);
        System.out.println(actualUser);
        assertEquals(expectedUser, actualUser);
    }
}
