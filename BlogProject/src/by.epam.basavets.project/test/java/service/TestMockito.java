package service;

import bean.User;
import command.Command;
import dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestMockito {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;


//    Command command = new Command();
//
//    PostDAO postDAO;
//    ModeratorDAO moderatorDAO;
//    PostCommentDAO postCommentDAO;
//    PostVoteDAO postVoteDAO;
//    SettingsDAO settingsDAO;
//
//    DataSource dataSource = new DataSource(moderatorDAO, postVoteDAO, postCommentDAO, postDAO, settingsDAO, userDAO);


    User user = new User(1, "Саша", "sasha@mail.ru", "987654321", LocalDateTime.now());
    User user1 = new User(1, "Вася", "fs@mail.ru", "1111", LocalDateTime.now());
    List<User> userList = new ArrayList<>();
    List<User> userList1 = new ArrayList<>();




    @Test
    public void testUserService()
    {
        userList.add(user);
        userList1.add(user1);
        userService.registerUser("Саша", "987654321", "sasha@mail.ru");


        //userService.deleteUserByEmail("sasha@mail.ru", "987654321");
        //System.out.println(dataSource.getUserDAO().getUsers());
        //System.out.println(userList);


        //Mockito.doReturn(user).when(userDAO.getUsers());
        //Mockito.doThrow(userService.registerUser("Саша", "987654321", "sasha@mail.ru"))
        //        .doAnswer("Пользователь - " + user.getName() + " успешно зарегистрирован");

        //Mockito.doThrow(userDAO.toString()).when(userService.registerUser("Саша", "987654321", "sasha@mail.ru"));
        Mockito.when(userDAO.getUsers()).thenReturn(userList);




        //        .
        //dataSource.getUserDAO().read();
        // определяем поведение калькулятора для операции сложения
       // userServiceMock.registerUser("Вася", "111", "vas@mail.ru");

        //Mockito.when(userService.registerUser("Вася", "111", "vas@mail.ru"))
        //        .then(userService.registerUser("Вася", "111", "vas@mail.ru"));





        //when(calc.add(10.0, 20.0)).thenReturn(30.0);

        // проверяем действие сложения
        //assertEquals(calc.add(10, 20), 30.0, 0);
        // проверяем выполнение действия
        //verify(mcalc).add(10.0, 20.0);

        // определение поведения с использованием doReturn
        //doReturn(15.0).when(mcalc).add(10.0, 5.0);

        // проверяем действие сложения
        //assertEquals(calc.add(10.0, 5.0), 15.0, 0);
        //verify(mcalc).add(10.0, 5.0);
    }
}
