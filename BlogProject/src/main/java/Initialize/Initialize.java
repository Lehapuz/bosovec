package Initialize;

import Bean.Moderator;
import Bean.User;
import DAO.ModeratorDAO;
import DAO.UserDAO;

import java.time.LocalDateTime;

public class Initialize {
    public static void initialize(){
        System.out.println("Привет");

        User user = new User();
        user.setId(1);
        user.setEmail("sasha@mail.ru");
        user.setName("sasha");
        user.setPassword("123");
        user.setRegTime(LocalDateTime.now());

        User user1 = new User();
        user1.setId(2);
        user1.setEmail("bas@mail.ru");
        user1.setName("bas");
        user1.setPassword("456");
        user1.setRegTime(LocalDateTime.now());

        UserDAO userDAO = new UserDAO();

        userDAO.addUser(user);
        userDAO.addUser(user1);

        userDAO.read();
        System.out.println("           ");

        userDAO.deleteUser(user);

        userDAO.read();
        System.out.println("           ");


        User user2 = new User();
        user2.setId(3);
        user2.setEmail("ivan@mail.ru");
        user2.setName("ivan");
        user2.setPassword("958");
        user2.setRegTime(LocalDateTime.now());
//       System.out.println(user.getRegTime());

        User user3 = new User();
        user3.setId(4);
        user3.setEmail("ola@mail.ru");
        user3.setName("ola");
        user3.setPassword("987");
        user3.setRegTime(LocalDateTime.now());

        userDAO.addUser(user2);
        userDAO.addUser(user3);

        userDAO.read();
        System.out.println("           ");


        User user4 = new User();
        user4.setId(5);
        user4.setEmail("leha@mail.ru");
        user4.setName("leha");
        user4.setPassword("888");
        user4.setRegTime(LocalDateTime.now());

        userDAO.updateUser(user3, user4);
        userDAO.read();
        System.out.println("           ");


        Moderator moderator = new Moderator();
        moderator.setId(1);
        moderator.setEmail("moderator@mail.ru");
        moderator.setName("moderator");
        moderator.setRegTime(LocalDateTime.now());
        moderator.setPassword("123456789");

        ModeratorDAO moderatorDAO = new ModeratorDAO();
        moderatorDAO.addModerator(moderator);
        moderatorDAO.read();
        System.out.println("           ");

        Moderator moderator1 = new Moderator();
        moderator1.setId(2);
        moderator1.setEmail("moderator10@mail.ru");
        moderator1.setName("newmoderator");
        moderator1.setRegTime(LocalDateTime.now());
        moderator1.setPassword("987654321");

        moderatorDAO.updateModerator(moderator, moderator1);
        moderatorDAO.read();
        System.out.println("           ");

        moderatorDAO.deleteModerator(moderator1);
        moderatorDAO.read();
        System.out.println("           ");











//
//        Post post = new Post();
//        post.setId(1);
//        post.setText("Пост про котов");
//        Post post1 = new Post();
//        post.setId(2);
//        post.setText("Пост про собак");
//        Post post2 = new Post();
//        post.setId(3);
//        post.setText("Пост про зайцев");





    }
}
