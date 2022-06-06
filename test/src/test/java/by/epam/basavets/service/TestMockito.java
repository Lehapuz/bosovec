package by.epam.basavets.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestMockito {

    @InjectMocks
    private UserService userService;
    private final String name = "Саша";
    private final String password = "987654321";
    private final String email = "sasha@mail.ru";


    @Test
    public void testRegisterUser() throws SQLException {
        userService = Mockito.mock(UserService.class);
        Mockito.doNothing().when(userService).registerUser(Mockito.isA(String.class),
                Mockito.isA(String.class), Mockito.isA(String.class));
        userService.registerUser(name, password, email);
        Mockito.verify(userService, Mockito.times(1)).registerUser("Саша", "987654321", "sasha@mail.ru");
    }

//    @Test
//    public void testUpdateUser() {
//        userService = Mockito.mock(UserService.class);
//        Mockito.doNothing().doThrow().when(userService).updateUserByEmail(Mockito.isA(String.class),
//                Mockito.isA(String.class), Mockito.isA(String.class), Mockito.isA(String.class));
//        String newPassword = "111111";
//        userService.updateUserByEmail(email, password, name, newPassword);
//        Mockito.verify(userService, Mockito.times(1)).updateUserByEmail("sasha@mail.ru", "987654321", "Саша", "111111");
//    }
//
//    @Test
//    public void testDeleteUser() {
//        userService = Mockito.mock(UserService.class);
//        Mockito.doNothing().doThrow().when(userService).deleteUserByEmail(Mockito.isA(String.class),
//                Mockito.isA(String.class));
//        userService.deleteUserByEmail(email, password);
//        Mockito.verify(userService, Mockito.times(1)).deleteUserByEmail("sasha@mail.ru", "987654321");
//    }
//
//    @Test
//    public void testAuthorithationUser() {
//        userService = Mockito.mock(UserService.class);
//        Mockito.doNothing().doThrow().when(userService).authorithationUser(Mockito.isA(String.class),
//                Mockito.isA(String.class));
//        userService.authorithationUser(email, password);
//        Mockito.verify(userService, Mockito.times(1)).authorithationUser("sasha@mail.ru", "987654321");
//    }

    @Test
    public void testGetAuthorithatedUser() {
        boolean expected = false;
        userService = Mockito.mock(UserService.class);
        Mockito.doNothing().when(userService).exit();
        userService.exit();
        Mockito.when(userService.getAuthorithated()).thenReturn(false);
        assertEquals(expected, userService.getAuthorithated());
    }
}
