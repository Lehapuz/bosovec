package by.epam.basavets.service;

import by.epam.basavets.bean.RoleTypes;
import by.epam.basavets.bean.Role;
import by.epam.basavets.bean.User;
import by.epam.basavets.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestMockito {

    @InjectMocks
    private UserService userService;
    private final String name = "Саша";
    private final String password = "987654321";
    private final String email = "sasha@mail.ru";
    private final Role role = new Role(1, RoleTypes.USER);
    private final User user = new User(1, "Саша", "sasha@mail.ru", "987654321", LocalDateTime.now(), role, 1);


    @Test
    public void testRegisterUser() throws SQLException, IOException {
        userService = Mockito.mock(UserService.class);
        Mockito.doNothing().when(userService).registerUser(Mockito.isA(String.class),
                Mockito.isA(String.class), Mockito.isA(String.class));
        userService.registerUser(name, password, email);
        Mockito.verify(userService, Mockito.times(1)).registerUser("Саша", "987654321", "sasha@mail.ru");
    }

    @Test
    public void testUpdateUser() {
        userService = Mockito.mock(UserService.class);
        Mockito.doNothing().doThrow().when(userService).updateUserByEmail(Mockito.isA(User.class), Mockito.isA(String.class),
                Mockito.isA(String.class), Mockito.isA(String.class), Mockito.isA(String.class));
        String newPassword = "111111";
        userService.updateUserByEmail(user, email, password, name, newPassword);
        Mockito.verify(userService, Mockito.times(1)).updateUserByEmail(user, "sasha@mail.ru", "987654321", "Саша", "111111");
    }

    @Test
    public void testDeleteUser() {
        userService = Mockito.mock(UserService.class);
        Mockito.doNothing().doThrow().when(userService).deleteUserByEmail(Mockito.isA(User.class), Mockito.isA(String.class),
                Mockito.isA(String.class));
        userService.deleteUserByEmail(user, email, password);
        Mockito.verify(userService, Mockito.times(1)).deleteUserByEmail(user, "sasha@mail.ru", "987654321");
    }

    @Test
    public void testAuthorizationUser() {
        userService = Mockito.mock(UserService.class);
        User expectedUser = new User(2, "Саша", "sasha@mail.rus", "987654320", LocalDateTime.now(), role, 1);
        Mockito.when(userService.authorizationUser("sasha@mail.rus", "987654320")).thenReturn(expectedUser);
        assertEquals(expectedUser, userService.authorizationUser("sasha@mail.rus", "987654320"));
    }
}
